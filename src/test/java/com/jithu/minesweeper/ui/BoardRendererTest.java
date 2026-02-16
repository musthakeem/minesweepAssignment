package com.jithu.minesweeper.ui;

import com.jithu.minesweeper.domain.Board;
import com.jithu.minesweeper.domain.Coordinate;
import com.jithu.minesweeper.ui.BoardRenderer;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BoardRendererTest {


    @Test
    void hiddenBoardShowsAllUnderscores() {
        Board board = new Board(2);
        board.initialize(Set.of(new Coordinate(0, 0)));

        String rendered = new BoardRenderer().render(board, false);
        assertTrue(rendered.contains("_"));
    }

    @Test
    void revealedCellShowsAdjacentMineCount() {
        Board board = new Board(3);
        board.initialize(Set.of(new Coordinate(0, 0)));
        board.cellAt(new Coordinate(0, 1)).reveal();

        String rendered = new BoardRenderer().render(board, false);
        assertTrue(rendered.contains("1"));
    }

    @Test
    void revealAllShowsMinesAsAsterisks() {
        Board board = new Board(2);
        board.initialize(Set.of(new Coordinate(0, 0)));

        String rendered = new BoardRenderer().render(board, true);
        assertTrue(rendered.contains("*"));
    }

    @Test
    void revealedZeroCellShowsZero() {
        Board board = new Board(2);
        board.initialize(Set.of());
        board.cellAt(new Coordinate(0, 0)).reveal();

        String rendered = new BoardRenderer().render(board, false);
        assertTrue(rendered.contains("0"));
    }

    @Test
    void columnHeadersMatchGridSize() {
        Board board = new Board(4);
        board.initialize(Set.of());

        String rendered = new BoardRenderer().render(board, false);
        assertTrue(rendered.contains("  1 2 3 4"));
    }
   
}