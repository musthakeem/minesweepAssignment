package com.jithu.minesweeper.mines;

import com.jithu.minesweeper.domain.Coordinate;
import com.jithu.minesweeper.mines.RandomMinePlacer;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RandomMinePlacerTest {

    @Test
    void placesExactNumberOfUniqueMines() {
        RandomMinePlacer placer = new RandomMinePlacer(new Random(123));
        Set<Coordinate> mines = placer.placeMines(10, 20);
        assertEquals(20, mines.size());
    }

    @Test
    void placeZeroMinesLeavesEmptyBoard() {
        RandomMinePlacer placer = new RandomMinePlacer(new Random(456));
        Set<Coordinate> mines = placer.placeMines(8, 0);
        assertTrue(mines.isEmpty());
    }

    @Test
    void allMinesPlacedInUniquePositions() {
        RandomMinePlacer placer = new RandomMinePlacer(new Random(789));
        Set<Coordinate> mines = placer.placeMines(5, 10);
        assertEquals(10, mines.size());
    }

    @Test
    void consistentMineCountAcrossMultipleRuns() {
        RandomMinePlacer placer = new RandomMinePlacer(new Random(321));
        for (int i = 0; i < 5; i++) {
            Set<Coordinate> mines = placer.placeMines(7, 15);
            assertEquals(15, mines.size());
        }
    }

    @Test
    void minesAreWithinBounds() {
        RandomMinePlacer placer = new RandomMinePlacer(new Random(999));
        int size = 5;
        Set<Coordinate> mines = placer.placeMines(size, 8);
        for (Coordinate c : mines) {
            assertTrue(c.row() >= 0 && c.row() < size);
            assertTrue(c.col() >= 0 && c.col() < size);
        }
    }

    @Test
    void deterministicWithSameSeed() {
        RandomMinePlacer p1 = new RandomMinePlacer(new Random(42));
        RandomMinePlacer p2 = new RandomMinePlacer(new Random(42));
        assertEquals(p1.placeMines(6, 10), p2.placeMines(6, 10));
    }
}
