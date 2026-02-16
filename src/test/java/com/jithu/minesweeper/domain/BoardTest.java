package com.jithu.minesweeper.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;





class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(5); // Create a 5x5 board for testing
    }

    @Test
    void testBoardInitialization() {
        assertEquals(5, board.getSize(), "Board size should be 5");
    }

    @Test
    void testInitializeWithMines() {
        Set<Coordinate> mines = Set.of(new Coordinate(1, 1), new Coordinate(2, 2));
        board.initialize(mines);

        assertTrue(board.cellAt(new Coordinate(1, 1)).isMine(), "Cell (1,1) should be a mine");
        assertTrue(board.cellAt(new Coordinate(2, 2)).isMine(), "Cell (2,2) should be a mine");
        assertFalse(board.cellAt(new Coordinate(0, 0)).isMine(), "Cell (0,0) should not be a mine");
    }

    @Test
    void testIsValidCoordinate() {
        assertTrue(board.isValid(new Coordinate(0, 0)), "Coordinate (0,0) should be valid");
        assertTrue(board.isValid(new Coordinate(4, 4)), "Coordinate (4,4) should be valid");
        assertFalse(board.isValid(new Coordinate(5, 5)), "Coordinate (5,5) should be invalid");
        assertFalse(board.isValid(new Coordinate(-1, 0)), "Coordinate (-1,0) should be invalid");
    }

    @Test
    void testCellAt() {
        Set<Coordinate> mines = Set.of(new Coordinate(1, 1));
        board.initialize(mines);

        Cell cell = board.cellAt(new Coordinate(1, 1));
        assertTrue(cell.isMine(), "Cell (1,1) should be a mine");

        assertThrows(IllegalArgumentException.class, () -> board.cellAt(new Coordinate(5, 5)),
                "Accessing out-of-bounds coordinate should throw an exception");
    }

    @Test
    void testUnrevealedNonMineCount() {
        Set<Coordinate> mines = Set.of(new Coordinate(1, 1), new Coordinate(2, 2));
        board.initialize(mines);

        long unrevealedNonMineCount = board.unrevealedNonMineCount();
        assertEquals(23, unrevealedNonMineCount, "There should be 23 unrevealed non-mine cells on a 5x5 board with 2 mines");
    }

    // Test adjacency counts for a single mine in the middle of the board
    @Test
    void testCalculateAdjacency() {
        Set<Coordinate> mines = Set.of(new Coordinate(1, 1));
        board.initialize(mines);

        assertEquals(1, board.cellAt(new Coordinate(0, 0)).getAdjacentMines(), "Cell (0,0) should have 1 adjacent mine");
        assertEquals(1, board.cellAt(new Coordinate(0, 1)).getAdjacentMines(), "Cell (0,1) should have 1 adjacent mine");
        assertEquals(1, board.cellAt(new Coordinate(0, 2)).getAdjacentMines(), "Cell (0,2) should have 1 adjacent mine");
        assertEquals(1, board.cellAt(new Coordinate(1, 0)).getAdjacentMines(), "Cell (1,0) should have 1 adjacent mine");
        assertEquals(1, board.cellAt(new Coordinate(1, 2)).getAdjacentMines(), "Cell (1,2) should have 1 adjacent mine");
        assertEquals(1, board.cellAt(new Coordinate(2, 0)).getAdjacentMines(), "Cell (2,0) should have 1 adjacent mine");
        assertEquals(1, board.cellAt(new Coordinate(2, 1)).getAdjacentMines(), "Cell (2,1) should have 1 adjacent mine");
        assertEquals(1, board.cellAt(new Coordinate(2, 2)).getAdjacentMines(), "Cell (2,2) should have 1 adjacent mine");
    }

    // Test adjacency counts for a mine in the corner, edge, and center
    @Test
    void adjacencyCornerEdgeCenterCountsCorrectly() {
        Board board = new Board(3);
        board.initialize(Set.of(new Coordinate(0, 0), new Coordinate(2, 2)));

        assertEquals(2, board.cellAt(new Coordinate(1, 1)).getAdjacentMines());
        assertEquals(1, board.cellAt(new Coordinate(0, 1)).getAdjacentMines());
        assertEquals(1, board.cellAt(new Coordinate(1, 2)).getAdjacentMines());
        assertTrue(board.cellAt(new Coordinate(0, 0)).isMine());
        assertTrue(board.cellAt(new Coordinate(2, 2)).isMine());
    }

    // Test adjacency counts when there are multiple mines around a cell
    @Test
    void adjacencyWithSingleMineInMiddle() {
        Board board = new Board(3);
        board.initialize(Set.of(new Coordinate(1, 1)));

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                var coord = new Coordinate(r, c);
                if (!board.cellAt(coord).isMine()) {
                    assertEquals(1, board.cellAt(coord).getAdjacentMines());
                }
            }
        }
    }
}