package com.jithu.minesweeper;

import com.jithu.minesweeper.domain.Coordinate;
import com.jithu.minesweeper.ui.CoordinateParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateParserTest {

    @Test
    void parsesValidUppercase() {
        assertEquals(new Coordinate(0, 0), CoordinateParser.parse("A1"));
        assertEquals(new Coordinate(3, 9), CoordinateParser.parse("D10"));
    }

    @Test
    void parsesValidLowercaseAndWhitespace() {
        assertEquals(new Coordinate(1, 2), CoordinateParser.parse("  b3 "));
    }

    @Test
    void rejectsEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> CoordinateParser.parse(""));
        assertThrows(IllegalArgumentException.class, () -> CoordinateParser.parse("   "));
    }

    @Test
    void rejectsMissingColumn() {
        assertThrows(IllegalArgumentException.class, () -> CoordinateParser.parse("A"));
    }

    @Test
    void rejectsNonNumericColumn() {
        assertThrows(IllegalArgumentException.class, () -> CoordinateParser.parse("A-"));
        assertThrows(IllegalArgumentException.class, () -> CoordinateParser.parse("A1B"));
    }

    @Test
    void rejectsColumnZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> CoordinateParser.parse("A0"));
        assertThrows(IllegalArgumentException.class, () -> CoordinateParser.parse("A-1"));
    }

    @Test
    void rejectsInvalidRowLabel() {
        assertThrows(IllegalArgumentException.class, () -> CoordinateParser.parse("11"));
        assertThrows(IllegalArgumentException.class, () -> CoordinateParser.parse("@1"));
    }
}
