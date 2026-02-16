package com.jithu.minesweeper.ui;

import java.util.Scanner;
import java.util.List;

public final class SystemConsole implements Console {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void printErrors(List<String> errors) {
        for (String error : errors) {
            System.out.println("Error: " + error);
        }
    }

    @Override
    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        while (true) {
            String line = readLine(prompt);
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                println("Please enter a valid integer.");
            }
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

        System.out.println(banner);
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
        System.out.println(banner);
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
        System.out.println(banner);
    }
}
