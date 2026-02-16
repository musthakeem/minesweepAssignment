package com.jithu.minesweeper.game;

import com.jithu.minesweeper.domain.GameStatus;

/**
 * Result of a reveal operation.
 */
public record RevealResult(GameStatus status, boolean hitMine, boolean alreadyRevealed, int adjacentMines) { }
