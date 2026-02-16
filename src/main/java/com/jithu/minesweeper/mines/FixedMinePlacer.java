package com.jithu.minesweeper.mines;

import com.jithu.minesweeper.domain.Coordinate;
import com.jithu.minesweeper.mines.MinePlacer;

import java.util.Set;

public final class FixedMinePlacer implements MinePlacer {
    
    private final Set<Coordinate> mines;

    public FixedMinePlacer(Set<Coordinate> mines) {
        this.mines = mines;
    }

    @Override
    public Set<Coordinate> placeMines(int size, int mineCount) {
        return mines;
    }
}
