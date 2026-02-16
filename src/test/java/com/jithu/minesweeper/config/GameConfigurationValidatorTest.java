package com.jithu.minesweeper.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameConfigurationValidatorTest {

    private GameConfigurationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new GameConfigurationValidator();
    }

    @Test
    void testValidateNullConfig() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.isValid());
        assertEquals("Configuration cannot be null.", result.getErrors().get(0));
    }

    @Test
    void testValidateValidConfig() {
        GameConfiguration config = new GameConfiguration(10, 10);
        ValidationResult result = validator.validate(config);
        assertTrue(result.isValid());
    }

    @Test
    void testValidateGridSizeTooLarge() {
        GameConfiguration config = new GameConfiguration(27, 10);
        ValidationResult result = validator.validate(config);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Invalid Configuration: Grid size must be at most 26."));
    }

    @Test
    void testValidateMineCountExceedsRatio() {
        GameConfiguration config = new GameConfiguration(10, 36);
        ValidationResult result = validator.validate(config);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Invalid Configuration: Mine count must be <= 35 for a 10x10 grid."));
    }

    @Test
    void testValidateMineCountExactlyAtRatio() {
        GameConfiguration config = new GameConfiguration(10, 35);
        ValidationResult result = validator.validate(config);
        assertTrue(result.isValid());
    }
}