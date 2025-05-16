package com.example.rps.game;

import com.example.rps.model.Move;
import java.util.Objects;

/**
 * Represents the result of a single round of Rock-Paper-Scissors.
 * This is a record, so it's immutable and provides default implementations
 * for constructor, getters, equals, hashCode, and toString.
 *
 * @param player1Move The move made by player 1.
 * @param player2Move The move made by player 2.
 * @param outcome The outcome of the round from player 1's perspective.
 * @param description A human-readable string describing what happened in the round.
 */
public record Result(Move player1Move, Move player2Move, Outcome outcome, String description) {
    /**
     * Defines the possible outcomes of a single round from player 1's perspective.
     */
    public enum Outcome {
        /** Player 1 wins the round. */
        PLAYER1_WIN,
        /** Player 2 wins the round. */
        PLAYER2_WIN,
        /** The round is a tie. */
        TIE
    }

    /**
     * Compact constructor for the Result record.
     * Ensures that all components are non-null.
     *
     * @param player1Move The move made by player 1.
     * @param player2Move The move made by player 2.
     * @param outcome The outcome of the round.
     * @param description A description of the round's events.
     * @throws NullPointerException if any parameter is null.
     */
    public Result {
        Objects.requireNonNull(player1Move);
        Objects.requireNonNull(player2Move);
        Objects.requireNonNull(outcome);
        Objects.requireNonNull(description);
    }
}
