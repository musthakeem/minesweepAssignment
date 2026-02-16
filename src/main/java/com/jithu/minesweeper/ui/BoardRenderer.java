package com.jithu.minesweeper.ui;

import com.jithu.minesweeper.domain.Board;
import com.jithu.minesweeper.domain.Cell;
import com.jithu.minesweeper.domain.Coordinate;


/**
 * The BoardRenderer class converts a Minesweeper board into a readable string format.
 * It displays the board as a grid with row and column coordinates.
 * 
 * The rendered output shows:
 * - Unrevealed cells
 * - Revealed cells with their adjacent mine counts
 * - Mines (when the revealAll option is enabled)
 */

public final class BoardRenderer {

    /**
     * Renders the given board as a formatted string grid with row and column labels.
     * Unrevealed cells are displayed as underscores ("_"), revealed safe cells show
     * the number of adjacent mines, and mines are displayed as asterisks ("*").
     * Mines are only shown if {@code revealAll} is true or if the cell has already been revealed.
     *
     * @param board the board to render
     * @param revealAll if true, displays all cells (including mines); 
     *                  if false, displays only revealed cells
     * @return a formatted string representation of the board with row letters and column numbers
     */
    public String render(Board board, boolean revealAll) {
        int size = board.getSize();
        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        for (int c = 1; c <= size; c++) sb.append(c).append(' ');
        sb.append(System.lineSeparator());

        for (int r = 0; r < size; r++) {
            sb.append((char) ('A' + r)).append(' ');
            for (int c = 0; c < size; c++) {
                Cell cell = board.cellAt(new Coordinate(r, c));
                if (revealAll && cell.isMine()) {
                    sb.append("* ");
                } else if (!cell.isRevealed()) {
                    sb.append("_ ");
                } else if (cell.isMine()) {
                    sb.append("* ");
                } else {
                    sb.append(cell.getAdjacentMines()).append(' ');
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
