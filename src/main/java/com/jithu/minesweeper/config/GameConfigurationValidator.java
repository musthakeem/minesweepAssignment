package com.jithu.minesweeper.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Validates the game configuration parameters.
 */
public final class GameConfigurationValidator {

    protected static final double MAX_MINE_RATIO = 0.35;

    /**
     * Validates the provided GameConfiguration.
     *
     * @param config the GameConfiguration to validate
     * @return a ValidationResult indicating validity and any errors
     */
    public ValidationResult validate(GameConfiguration config) {
        if (config == null) {
            return ValidationResult.invalid("Configuration cannot be null.");
        }

        List<String> errors = new ArrayList<>();

        int size = config.getGridSize();
        if (size < 2) {
            errors.add("Invalid Configuration: Grid size must be at least 2.");
        } else if (size > 26) {
            errors.add("Invalid Configuration: Grid size must be at most 26.");
        }

        int mines = config.getMineCount();
        if (mines <= 0) {
            errors.add("Invalid Configuration: Mine count must be positive.");
        }

        // Only check ratio if both size and mines are individually valid
        if (size >= 2 && size <= 26 && mines > 0) {
            int maxMines = (int) Math.floor(size * size * MAX_MINE_RATIO);
            if (mines > maxMines) {
                errors.add("Invalid Configuration: Mine count must be <= " + maxMines
                        + " for a " + size + "x" + size + " grid.");
            }
        }

        return errors.isEmpty() ? ValidationResult.valid() : ValidationResult.invalid(errors);
    }
}
