package com.jithu.minesweeper.ui;

import com.jithu.minesweeper.ui.Console;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class FakeConsole implements Console {

    private final Deque<String> inputs = new ArrayDeque<>();
    private final List<String> outputs = new ArrayList<>();

    public void addInput(String line) {
        inputs.addLast(line);
    }

    public List<String> outputs() {
        return outputs;
    }

    @Override
    public void println(String message) {
        outputs.add(message);
    }

    @Override
    public String readLine(String prompt) {
        outputs.add(prompt);
        if (inputs.isEmpty()) throw new IllegalStateException("No more inputs for prompt: " + prompt);
        return inputs.removeFirst();
    }

    @Override
    public int readInt(String prompt) {
        String s = readLine(prompt);
        return Integer.parseInt(s.trim());
    }

    @Override
    public void printErrors(List<String> errors) {
        for (String error : errors) {
            outputs.add("Error: " + error);
        }
    }

    @Override
    public void printWelcomeMessage() {
        String banner = """
        █   █ ███ █   ███  █  █   █ ███     ███  █      █   █ ███ █  █ ███  ██ █   █ ███ ███ ██  
        █   █ █   █   █   █ █ ██ ██ █        █  █ █     ██ ██  █  ██ █ █   █   █   █ █   █   █ █ 
        █ █ █ ██  █   █   █ █ █ █ █ ██       █  █ █     █ █ █  █  █ ██ ██  █   █ █ █ ██  ██  ██  
        █ █ █ █   █   █   █ █ █   █ █        █  █ █     █   █  █  █ ██ █    █  █ █ █ █   █   █   
        ██ ██ █   █   █   █ █ █   █ █        █  █ █     █   █  █  █  █ █     █ ██ ██ █   █   █   
        █   █ ███ ███ ███  █  █   █ ███      █   █      █   █ ███ █  █ ███ ██  █   █ ███ ███ █   
        """;
        outputs.add(banner);
    }

    @Override
    public void printLostMessage() {
        String banner = """
         ██  █  █   █ ███      █  █ █ ███ ██  
        █   ███ ██ ██ █       █ █ █ █ █   █ █ 
        █ █ █ █ █ █ █ ██      █ █ █ █ ██  ██  
        █ █ ███ █   █ █       █ █ █ █ █   █ █ 
        █ █ █ █ █   █ █       █ █  █  █   █ █ 
         ██ █ █ █   █ ███      █   █  ███ █ █ 
        """;
        outputs.add(banner);
    }

        @Override
    public void printWinMessage() {
        String banner = """
        █ █  █  █ █     █   █  █  █  █ 
        █ █ █ █ █ █     █   █ █ █ ██ █ 
         █  █ █ █ █     █ █ █ █ █ █ ██ 
         █  █ █ █ █     █ █ █ █ █ █ ██ 
         █  █ █ █ █     ██ ██ █ █ █  █ 
         █   █   █      █   █  █  █  █ 
        """;
        outputs.add(banner);
    }
}