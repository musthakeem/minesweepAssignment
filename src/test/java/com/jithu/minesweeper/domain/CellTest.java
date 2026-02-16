package com.jithu.minesweeper.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class CellTest {

    @Test
    void testCellInitialization() {
        Cell cellWithMine = new Cell(true);
        Cell cellWithoutMine = new Cell(false);

        assertTrue(cellWithMine.isMine(), "Cell with mine should return true for isMine()");
        assertFalse(cellWithoutMine.isMine(), "Cell without mine should return false for isMine()");
        assertFalse(cellWithMine.isRevealed(), "New cell should not be revealed");
        assertEquals(0, cellWithMine.getAdjacentMines(), "New cell should have 0 adjacent mines");
    }

    @Test
    void testReveal() {
        Cell cell = new Cell(false);

        assertFalse(cell.isRevealed(), "Cell should not be revealed initially");
        cell.reveal();
        assertTrue(cell.isRevealed(), "Cell should be revealed after calling reveal()");
    }

    @Test
    void testSetAndGetAdjacentMines() {
        Cell cell = new Cell(false);

        assertEquals(0, cell.getAdjacentMines(), "Initial adjacent mines should be 0");
        cell.setAdjacentMines(3);
        assertEquals(3, cell.getAdjacentMines(), "Adjacent mines should be updated to 3");
    }
}