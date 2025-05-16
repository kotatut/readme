package com.example.rps.ui;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of {@link InputHandler} that reads input from the system console.
 */
public class ConsoleInputHandler implements InputHandler {
    private static final Logger LOGGER = Logger.getLogger(ConsoleInputHandler.class.getName());
    private final Scanner scanner;

    /**
     * Constructs a new ConsoleInputHandler, initializing a {@link Scanner} to read from {@link System#in}.
     */
    public ConsoleInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of text input from the console.
     *
     * @return The string input by the user.
     */
    @Override
    public String readUserInput() {
        return scanner.nextLine();
    }

    /**
     * Reads an integer input from the console, displaying a prompt.
     * If the user enters non-integer input, it logs a warning, displays an error message,
     * and re-prompts until a valid integer is entered.
     *
     * @param prompt The message to display to the user before reading input.
     * @return The integer input by the user.
     */
    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        while (true) {
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Invalid integer input: \"{0}\" for prompt: \"{1}\"", new Object[]{line, prompt});
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.println(prompt);
            }
        }
    }
}
