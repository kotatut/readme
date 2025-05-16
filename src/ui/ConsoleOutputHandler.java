package com.example.rps.ui;

/**
 * An implementation of {@link OutputHandler} that displays messages to the system console.
 */
public class ConsoleOutputHandler implements OutputHandler {

    /**
     * Displays the given message to {@link System#out} followed by a newline.
     *
     * @param message The message to be displayed.
     */
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
