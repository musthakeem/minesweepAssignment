package com.jithu.minesweeper.ui;

import com.jithu.minesweeper.domain.Coordinate;

/**
 * Parses coordinates like A1, b3 etc. Does not validate board bounds.
 */
public final class CoordinateParser {

    public static Coordinate parse(String input) {
        if (input == null) throw new IllegalArgumentException("Input cannot be null.");
        String trimmed = input.trim().toUpperCase();
        if (trimmed.isEmpty()) throw new IllegalArgumentException("Input cannot be empty.");

        char rowChar = trimmed.charAt(0);
        if (rowChar < 'A' || rowChar > 'Z') {
            throw new IllegalArgumentException("Row must be a letter (A-Z).");
        }
        if (trimmed.length() == 1) {
            throw new IllegalArgumentException("Column is missing.");
        }

        String colPart = trimmed.substring(1);
        int col;
        try {
            col = Integer.parseInt(colPart);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Column must be a number.", ex);
        }
        if (col <= 0) throw new IllegalArgumentException("Column must be >= 1.");

        int row = rowChar - 'A';
        return new Coordinate(row, col - 1);
    }

    private CoordinateParser() { }
}
