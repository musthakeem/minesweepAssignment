package com.jithu.minesweeper.config;

/**
 * Immutable configuration for a Minesweeper game session.
 */
public final class GameConfiguration {
    private final int gridSize;
    private final int mineCount;

    /**
     * Constructs a new GameConfiguration with the specified grid size and mine count.
     *
     * @param gridSize the size of the grid
     * @param mineCount the number of mines
     * @throws IllegalArgumentException if gridSize or mineCount is not positive
     */
    public GameConfiguration(int gridSize, int mineCount) {
         if (gridSize < 2) {
            throw new IllegalArgumentException("Grid size must be greater than or equal to 2.");
        }

        if (mineCount <= 0) {
            throw new IllegalArgumentException("Mine count must be positive.");
        }

        this.gridSize = gridSize;
        this.mineCount = mineCount;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getMineCount() {
        return mineCount;
    }
}
