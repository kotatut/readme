package com.example.rps.ui;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    private final Scanner scanner;

    public ConsoleInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readUserInput() {
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        while (true) {
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.println(prompt);
            }
        }
    }
}
