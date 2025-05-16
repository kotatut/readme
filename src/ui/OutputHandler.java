package com.example.rps.ui;

/**
 * Defines the interface for handling output display.
 * This allows for different output destinations to be used (e.g., console, GUI).
 */
public interface OutputHandler {
    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    void displayMessage(String message);
}
