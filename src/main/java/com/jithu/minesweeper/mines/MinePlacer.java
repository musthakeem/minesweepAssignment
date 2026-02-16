package com.jithu.minesweeper.mines;

import com.jithu.minesweeper.domain.Coordinate;

import java.util.Set;

public interface MinePlacer {
    Set<Coordinate> placeMines(int size, int mineCount);
}
