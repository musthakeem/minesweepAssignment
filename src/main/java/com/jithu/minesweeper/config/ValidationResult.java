package com.jithu.minesweeper.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of validating a game configuration.
 */
public class ValidationResult {
    private final boolean valid;
    private final List<String> errors;

    /**
     * Constructs a ValidationResult with the specified validity and error messages.
     *
     * @param valid whether the configuration is valid
     * @param errors list of error messages if invalid
     */
    private ValidationResult(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = errors;
    }

    /**
     * Creates a ValidationResult representing a valid configuration.
     *
     * @return a ValidationResult indicating validity
     */
    public static ValidationResult valid() {
        return new ValidationResult(true, new ArrayList<>());
    }

    /**
     * Creates a ValidationResult representing an invalid configuration with a single error message.
     *
     * @param message the error message describing the validation failure
     * @return a ValidationResult indicating invalidity with the provided error message
     */
    public static ValidationResult invalid(String message) {
        List<String> errors = new ArrayList<>();
        errors.add(message);
        return new ValidationResult(false, errors);
    }
    
    /**
     * Creates a ValidationResult representing an invalid configuration with multiple error messages.
     *
     * @param errors the list of error messages describing the validation failures
     * @return a ValidationResult indicating invalidity with the provided error messages
     */
    public static ValidationResult invalid(List<String> errors) {
        return new ValidationResult(false, errors);
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }
    
}
