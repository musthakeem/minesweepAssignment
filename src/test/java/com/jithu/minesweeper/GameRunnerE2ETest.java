package com.jithu.minesweeper;

import com.jithu.minesweeper.config.GameConfiguration;
import com.jithu.minesweeper.domain.Coordinate;
import com.jithu.minesweeper.domain.GameStatus;
import com.jithu.minesweeper.game.Game;
import com.jithu.minesweeper.game.RecursiveRevealStrategy;
import com.jithu.minesweeper.ui.GameRunner;
import com.jithu.minesweeper.ui.FakeConsole;
import com.jithu.minesweeper.mines.FixedMinePlacer;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameRunnerE2ETest {

    @Test
    void runnerShowsWinMessageWhenAllNonMineCellsRevealed() {
        // 2x2 with mine at A1 (0,0). To win: reveal the other 3 cells.
        var game = new Game(new GameConfiguration(2, 1),
                new FixedMinePlacer(Set.of(new Coordinate(0, 0))),
                new RecursiveRevealStrategy());

        var console = new FakeConsole();
        console.addInput("A2");
        console.addInput("B1");
        console.addInput("B2");

        new GameRunner(console).run(game);

        assertEquals(GameStatus.WON, game.getStatus());
        assertTrue(console.outputs().stream().anyMatch(s -> s.contains("Congratulations")));
    }

    @Test
    void runnerShowsLossMessageWhenMineHit() {
        var game = new Game(new GameConfiguration(2, 1),
                new FixedMinePlacer(Set.of(new Coordinate(0, 0))),
                new RecursiveRevealStrategy());

        var console = new FakeConsole();
        console.addInput("A1"); // hit mine

        new GameRunner(console).run(game);

        assertEquals(GameStatus.LOST, game.getStatus());
        assertTrue(console.outputs().stream().anyMatch(s -> s.contains("detonated a mine")));
    }

    @Test
    void runnerRejectsOutOfBoundsCoordinateAndContinues() {
        var game = new Game(new GameConfiguration(2, 1),
                new FixedMinePlacer(Set.of()),
                new RecursiveRevealStrategy());

        var console = new FakeConsole();
        console.addInput("C1"); // out of bounds
        console.addInput("A1"); // valid

        new GameRunner(console).run(game);

        assertTrue(console.outputs().stream().anyMatch(s -> s.contains("out of bounds")));
        assertEquals(GameStatus.WON, game.getStatus());
    }
}
