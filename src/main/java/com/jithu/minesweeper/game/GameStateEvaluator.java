package com.jithu.minesweeper.game;

import com.jithu.minesweeper.domain.Board;
import com.jithu.minesweeper.domain.GameStatus;

/**
 * Stateless evaluation of game outcome.
 */
public final class GameStateEvaluator {
    public GameStatus evaluate(Board board, boolean hitMine) {
        if (hitMine) return GameStatus.LOST;
        if (board.unrevealedNonMineCount() == 0) return GameStatus.WON;
        return GameStatus.IN_PROGRESS;
    }
}
