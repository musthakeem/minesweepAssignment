package com.jithu.minesweeper.game;

import com.jithu.minesweeper.config.GameConfiguration;
import com.jithu.minesweeper.domain.Coordinate;
import com.jithu.minesweeper.domain.GameStatus;
import com.jithu.minesweeper.game.Game;
import com.jithu.minesweeper.game.RecursiveRevealStrategy;
import com.jithu.minesweeper.mines.FixedMinePlacer;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void hittingMineLosesGameAndRevealsMineCell() {
        var game = new Game(new GameConfiguration(3, 1),
                new FixedMinePlacer(Set.of(new Coordinate(1, 1))),
                new RecursiveRevealStrategy());

        var result = game.reveal(new Coordinate(1, 1));
        assertTrue(result.hitMine());
        assertEquals(GameStatus.LOST, game.getStatus());
        assertTrue(game.getBoard().cellAt(new Coordinate(1, 1)).isRevealed());
    }

    @Test
    void revealingAllNonMineCellsWinsGame() {
        var game = new Game(new GameConfiguration(2, 1),
                new FixedMinePlacer(Set.of(new Coordinate(0, 0))),
                new RecursiveRevealStrategy());

        game.reveal(new Coordinate(0, 1));
        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());

        game.reveal(new Coordinate(1, 0));
        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());

        game.reveal(new Coordinate(1, 1));
        assertEquals(GameStatus.WON, game.getStatus());
    }

    @Test
    void zeroMinesWinsAfterFirstRevealBecauseEverythingIsRevealedByCascade() {
        var game = new Game(new GameConfiguration(3, 1),
                new FixedMinePlacer(Set.of()),
                new RecursiveRevealStrategy());

        game.reveal(new Coordinate(1, 1));
        assertEquals(GameStatus.WON, game.getStatus());
    }

    @Test
    void reRevealingARevealedCellIsReportedAndDoesNotCrash() {
        var game = new Game(new GameConfiguration(2, 1),
                new FixedMinePlacer(Set.of()),
                new RecursiveRevealStrategy());

        var first = game.reveal(new Coordinate(0, 0));
        assertFalse(first.alreadyRevealed());

        var second = game.reveal(new Coordinate(0, 0));
        assertTrue(second.alreadyRevealed());
        assertEquals(GameStatus.WON, game.getStatus());
    }

    @Test
    void gameIgnoresRevealsAfterLoss() {
        var game = new Game(new GameConfiguration(2, 1),
                new FixedMinePlacer(Set.of(new Coordinate(0, 0))),
                new RecursiveRevealStrategy());

        game.reveal(new Coordinate(0, 0));
        assertEquals(GameStatus.LOST, game.getStatus());

        assertDoesNotThrow(() -> game.reveal(new Coordinate(1, 1)));
        assertEquals(GameStatus.LOST, game.getStatus());
    }
}
