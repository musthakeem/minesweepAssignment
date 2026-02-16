package com.jithu.minesweeper.mines;

import com.jithu.minesweeper.mines.FixedMinePlacer;
import com.jithu.minesweeper.domain.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



public class FixedMinePlacerTest {

    @Test
    void testPlaceMinesReturnsProvidedMines() {
        Set<Coordinate> expectedMines = new HashSet<>();
        expectedMines.add(new Coordinate(1, 1));
        expectedMines.add(new Coordinate(2, 2));

        FixedMinePlacer placer = new FixedMinePlacer(expectedMines);

        Set<Coordinate> actualMines = placer.placeMines(10, 5);

        assertNotNull(actualMines);
        assertEquals(2, actualMines.size());
        assertEquals(expectedMines, actualMines);
    }

    @Test
    void testPlaceMinesReturnsEmptySetWhenCountIsZero() {
        FixedMinePlacer placer = new FixedMinePlacer(Collections.emptySet());

        Set<Coordinate> result = placer.placeMines(10, 0);

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}