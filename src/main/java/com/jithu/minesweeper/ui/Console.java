package com.jithu.minesweeper.ui;

import java.util.List;

public interface Console {
    void println(String message);
    void printWelcomeMessage();
    void printWinMessage();
    void printLostMessage();
    void printErrors(List<String> errors);
    String readLine(String prompt);
    int readInt(String prompt);
}
