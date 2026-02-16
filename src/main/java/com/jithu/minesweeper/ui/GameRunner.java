package com.jithu.minesweeper.ui;

import com.jithu.minesweeper.domain.GameStatus;
import com.jithu.minesweeper.game.Game;
import com.jithu.minesweeper.game.RevealResult;

/**
 * CLI game loop, separated from domain for testability.
 */
public final class GameRunner {
    private final Console console;
    private final BoardRenderer renderer;

    public GameRunner(Console console) {
        this(console, new BoardRenderer());
    }

    public GameRunner(Console console, BoardRenderer renderer) {
        this.console = console;
        this.renderer = renderer;
    }

    public void run(Game game) {
        console.println("");
        console.println("Here is your minefield:");
        console.println(renderer.render(game.getBoard(), false));

        while (game.getStatus() == GameStatus.IN_PROGRESS) {
            String input = console.readLine("Select a square to reveal (e.g. A1): ");
            try {
                var coordinate = CoordinateParser.parse(input);

                if (!game.getBoard().isValid(coordinate)) {
                    console.println("Invalid square: out of bounds.");
                    continue;
                }

                RevealResult result = game.reveal(coordinate);

                if (result.alreadyRevealed()) {
                    console.println("That square is already revealed.");
                } else if (result.hitMine()) {
                    console.printLostMessage();
                    console.println("Oh no, you detonated a mine!");
                } else {
                    console.println("This square contains " + result.adjacentMines() + " adjacent mines.");
                }

                console.println("");
                console.println("Here is your updated minefield:");
                console.println(renderer.render(game.getBoard(), game.getStatus() != GameStatus.IN_PROGRESS));

            } catch (IllegalArgumentException ex) {
                console.println("Invalid input: " + ex.getMessage());
            }
        }

        if (game.getStatus() == GameStatus.WON) {
            console.printWinMessage();
            console.println("Congratulations, you have won the game!");
        }
    }
}
