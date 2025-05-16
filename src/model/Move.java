package com.example.rps.model;

import java.util.Optional;

public enum Move {
    ROCK,
    PAPER,
    SCISSORS;

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
