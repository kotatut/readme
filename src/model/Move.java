package com.example.rps.model;

import java.util.Optional;

/**
 * Represents the possible moves in a Rock-Paper-Scissors game: ROCK, PAPER, SCISSORS.
 */
public enum Move {
    /** Represents the Rock move. */
    ROCK,
    /** Represents the Paper move. */
    PAPER,
    /** Represents the Scissors move. */
    SCISSORS;

    /**
     * Parses a string to a {@link Move} enum value, case-insensitive.
     *
     * @param text The string to parse (e.g., "Rock", "paper", "SCISSORS").
     * @return An {@link Optional} containing the matching {@link Move} if valid,
     *         or {@link Optional#empty()} if the text is null or not a valid move.
     */
    public static Optional<Move> fromString(String text) {
        if (text == null) {
            return Optional.empty();
        }
        String upperCaseText = text.toUpperCase();
        try {
            return Optional.of(Move.valueOf(upperCaseText));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    /**
     * Determines if this move beats another move according to standard
     * Rock-Paper-Scissors rules (Rock crushes Scissors, Scissors cuts Paper, Paper covers Rock).
     *
     * @param other The other move to compare against.
     * @return {@code true} if this move beats the {@code other} move, {@code false} otherwise (including ties).
     */
    public boolean beats(Move other) {
        if (this == other) {
            return false;
        }
        switch (this) {
            case ROCK:
                return other == SCISSORS;
            case PAPER:
                return other == ROCK;
            case SCISSORS:
                return other == PAPER;
            default:
                // Should not happen
                return false;
        }
    }
}
