package com.jithu.minesweeper.domain;

/**
 * Represents a cell on the board. Encapsulates state transitions.
 */
public final class Cell {
    private final boolean mine;
    private boolean revealed;
    private int adjacentMines;

    public Cell(boolean mine) {
        this.mine = mine;
        this.revealed = false;
        this.adjacentMines = 0;
    }

    public boolean isMine() {
        return mine;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        this.revealed = true;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }
}
