package com.jithu.minesweeper.game;

import com.jithu.minesweeper.domain.Board;
import com.jithu.minesweeper.domain.Coordinate;
import com.jithu.minesweeper.game.RecursiveRevealStrategy;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveRevealStrategyTest {

    @Test
    void revealingZeroCascadesToNeighborsUntilNumbers() {
        Board board = new Board(4);
        board.initialize(Set.of(new Coordinate(0, 0)));

        new RecursiveRevealStrategy().reveal(board, new Coordinate(3, 3));

        assertFalse(board.cellAt(new Coordinate(0, 0)).isRevealed());
        assertTrue(board.cellAt(new Coordinate(3, 3)).isRevealed());

        int revealed = 0;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (board.cellAt(new Coordinate(r, c)).isRevealed()) revealed++;
            }
        }
        assertTrue(revealed >= 12);
    }

    @Test
    void revealingNumberOnlyRevealsThatCell() {
        Board board = new Board(3);
        board.initialize(Set.of(new Coordinate(0, 0)));

        new RecursiveRevealStrategy().reveal(board, new Coordinate(0, 1));

        assertTrue(board.cellAt(new Coordinate(0, 1)).isRevealed());
        assertFalse(board.cellAt(new Coordinate(2, 2)).isRevealed());
    }

    @Test
    void reRevealingAlreadyRevealedCellIsIdempotent() {
        Board board = new Board(2);
        board.initialize(Set.of());

        var strategy = new RecursiveRevealStrategy();
        strategy.reveal(board, new Coordinate(0, 0));
        assertTrue(board.cellAt(new Coordinate(0, 0)).isRevealed());

        strategy.reveal(board, new Coordinate(0, 0));
        assertTrue(board.cellAt(new Coordinate(0, 0)).isRevealed());
    }
}
