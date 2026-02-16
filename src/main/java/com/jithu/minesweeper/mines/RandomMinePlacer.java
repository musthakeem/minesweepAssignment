package com.jithu.minesweeper.mines;

import com.jithu.minesweeper.domain.Coordinate;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Places mines randomly. Accepts Random for deterministic tests.
 */
public final class RandomMinePlacer implements MinePlacer {

    private final Random random;

    public RandomMinePlacer() {
        this(new Random());
    }

    public RandomMinePlacer(Random random) {
        this.random = random;
    }

    @Override
    public Set<Coordinate> placeMines(int size, int mineCount) {
        Set<Coordinate> mines = new HashSet<>();
        while (mines.size() < mineCount) {
            mines.add(new Coordinate(random.nextInt(size), random.nextInt(size)));
        }
        return mines;
    }
}
