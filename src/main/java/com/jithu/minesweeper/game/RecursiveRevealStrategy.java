package com.jithu.minesweeper.game;

import com.jithu.minesweeper.domain.Board;
import com.jithu.minesweeper.domain.Cell;
import com.jithu.minesweeper.domain.Coordinate;

/**
 * Flood-fill reveal strategy: reveals contiguous zero-adjacent regions and their boundary numbers.
 */
public final class RecursiveRevealStrategy implements RevealStrategy {

    @Override
    public void reveal(Board board, Coordinate coordinate) {
        revealInternal(board, coordinate);
    }

    private void revealInternal(Board board, Coordinate coordinate) {
        if (!board.isValid(coordinate)) return;

        Cell cell = board.cellAt(coordinate);
        if (cell.isRevealed() || cell.isMine()) return;

        cell.reveal();

        if (cell.getAdjacentMines() == 0) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr == 0 && dc == 0) continue;
                    revealInternal(board, new Coordinate(coordinate.row() + dr, coordinate.col() + dc));
                }
            }
        }
    }
}
