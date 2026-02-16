package com.jithu.minesweeper.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameConfigurationTest {

    @Test
    public void testValidConfiguration() {
        int gridSize = 10;
        int mineCount = 5;
        GameConfiguration config = new GameConfiguration(gridSize, mineCount);

        assertEquals(gridSize, config.getGridSize());
        assertEquals(mineCount, config.getMineCount());
    }

    @Test
    public void testNegativeGridSize() {
        assertThrows(IllegalArgumentException.class, () -> new GameConfiguration(-1, 5));
    }

    @Test
    public void testZeroGridSize() {
        assertThrows(IllegalArgumentException.class, () -> new GameConfiguration(0, 5));
    }

     @Test
    public void testOneGridSize() {
        assertThrows(IllegalArgumentException.class, () -> new GameConfiguration(1, 5));
    }

    @Test
    public void testNegativeMineCount() {
        assertThrows(IllegalArgumentException.class, () -> new GameConfiguration(10, -1));
    }

    @Test
    public void testZeroMineCount() {
        assertThrows(IllegalArgumentException.class, () -> new GameConfiguration(10, 0));
    }
}