package com.jithu.minesweeper;

import com.jithu.minesweeper.config.GameConfiguration;
import com.jithu.minesweeper.config.GameConfigurationValidator;
import com.jithu.minesweeper.game.Game;
import com.jithu.minesweeper.game.RecursiveRevealStrategy;
import com.jithu.minesweeper.mines.RandomMinePlacer;
import com.jithu.minesweeper.ui.GameRunner;
import com.jithu.minesweeper.ui.SystemConsole;

/**
 * Main entry point for the Minesweeper game. Handles user interaction and game loop.
 */
public final class Main {
    public static void main(String[] args) {
        var console = new SystemConsole();
        console.printWelcomeMessage();

        while (true) {
            try {
                int size = console.readInt("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
                int mines = console.readInt("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");

                var config = new GameConfiguration(size, mines);
                
                var validationResult = new GameConfigurationValidator().validate(config);
                if (!validationResult.isValid()) {
                    console.printErrors(validationResult.getErrors());
                    continue;
                }

                var game = new Game(config, new RandomMinePlacer(), new RecursiveRevealStrategy());
                new GameRunner(console).run(game);

                console.readLine("Press Enter to play again (or Ctrl+C to exit)...");
            } catch (IllegalArgumentException ex) {
                console.println("Invalid configurations: " + ex.getMessage());
            }
        }
    }
}
