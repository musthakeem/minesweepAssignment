package com.jithu.minesweeper.domain;

import java.util.Arrays;
import java.util.Set;

/**
 * Board holds the grid state and adjacency numbers.
 * Policy lives outside this class.
 */
public final class Board {
    private final int size;
    private final Cell[][] grid;

    public Board(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
    }

    public int getSize() {
        return size;
    }

    public void initialize(Set<Coordinate> mines) {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                boolean isMine = mines.contains(new Coordinate(r, c));
                grid[r][c] = new Cell(isMine);
            }
        }
        calculateAdjacency();
    }

    public boolean isValid(Coordinate coordinate) {
        return coordinate != null
                && coordinate.row() >= 0 && coordinate.row() < size
                && coordinate.col() >= 0 && coordinate.col() < size;
    }

    public Cell cellAt(Coordinate coordinate) {
        if (!isValid(coordinate)) {
            throw new IllegalArgumentException("Coordinate out of bounds: " + coordinate);
        }
        return grid[coordinate.row()][coordinate.col()];
    }

    public long unrevealedNonMineCount() {
        return Arrays.stream(grid)
                .flatMap(Arrays::stream)
                .filter(c -> !c.isMine() && !c.isRevealed())
                .count();
    }

    private void calculateAdjacency() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (!grid[r][c].isMine()) {
                    grid[r][c].setAdjacentMines(countAdjacentMines(r, c));
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int rr = row + dr;
                int cc = col + dc;
                if (rr == row && cc == col) continue;
                if (rr >= 0 && rr < size && cc >= 0 && cc < size) {
                    if (grid[rr][cc].isMine()) count++;
                }
            }
        }
        return count;
    }
}
