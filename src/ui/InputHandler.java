package com.example.rps.ui;

/**
 * Defines the interface for handling user input.
 * This allows for different input sources to be used (e.g., console, GUI).
 */
public interface InputHandler {
    /**
     * Reads a line of text input from the user.
     *
     * @return The string input by the user.
     */
    String readUserInput();

    /**
     * Reads an integer input from the user, displaying a prompt.
     * If the user enters non-integer input, implementations should handle
     * re-prompting until a valid integer is entered.
     *
     * @param prompt The message to display to the user before reading input.
     * @return The integer input by the user.
     */
    int readInt(String prompt);
}
