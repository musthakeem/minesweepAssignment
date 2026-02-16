package com.jithu.minesweeper.game;

import com.jithu.minesweeper.domain.Board;
import com.jithu.minesweeper.domain.Coordinate;

public interface RevealStrategy {
    void reveal(Board board, Coordinate coordinate);
}
