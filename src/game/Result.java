package com.example.rps.game;

import com.example.rps.model.Move;
import java.util.Objects;

// Represents the result of a single round.
// player1Move and player2Move are the moves made.
// outcome is from the perspective of player1.
// description provides a human-readable string of what happened.
public record Result(Move player1Move, Move player2Move, Outcome outcome, String description) {
    public enum Outcome { PLAYER1_WIN, PLAYER2_WIN, TIE }

    public Result {
        Objects.requireNonNull(player1Move);
        Objects.requireNonNull(player2Move);
        Objects.requireNonNull(outcome);
        Objects.requireNonNull(description);
    }
}
