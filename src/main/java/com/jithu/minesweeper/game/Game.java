package com.jithu.minesweeper.game;

import com.jithu.minesweeper.config.GameConfiguration;
import com.jithu.minesweeper.domain.Board;
import com.jithu.minesweeper.domain.Cell;
import com.jithu.minesweeper.domain.Coordinate;
import com.jithu.minesweeper.domain.GameStatus;
import com.jithu.minesweeper.mines.MinePlacer;

import java.util.Set;

/**
 * Orchestrates a single game session (stateful).
 */
public final class Game {
    private final Board board;
    private final RevealStrategy revealStrategy;
    private final GameStateEvaluator evaluator;
    private GameStatus status;

    public Game(GameConfiguration config, MinePlacer minePlacer, RevealStrategy revealStrategy) {
        this.board = new Board(config.getGridSize());
        Set<Coordinate> mines = minePlacer.placeMines(config.getGridSize(), config.getMineCount());
        this.board.initialize(mines);

        this.revealStrategy = revealStrategy;
        this.evaluator = new GameStateEvaluator();
        this.status = GameStatus.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public RevealResult reveal(Coordinate coordinate) {
        if (!board.isValid(coordinate)) {
            throw new IllegalArgumentException("Coordinate out of bounds.");
        }

        Cell cell = board.cellAt(coordinate);
        if (cell.isRevealed()) {
            return new RevealResult(status, false, true, cell.isMine() ? -1 : cell.getAdjacentMines());
        }

        if (status != GameStatus.IN_PROGRESS) {
            return new RevealResult(status, false, false, -1);
        }

        if (cell.isMine()) {
            cell.reveal();
            status = evaluator.evaluate(board, true);
            return new RevealResult(status, true, false, -1);
        }

        revealStrategy.reveal(board, coordinate);
        status = evaluator.evaluate(board, false);
        int adjacent = board.cellAt(coordinate).getAdjacentMines();
        return new RevealResult(status, false, false, adjacent);
    }
}
