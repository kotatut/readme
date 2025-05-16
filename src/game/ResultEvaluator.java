package com.example.rps.game;

import com.example.rps.model.Move;
import java.util.Objects;

/**
 * Evaluates the result of a Rock-Paper-Scissors round between two players.
 * Determines the winner or if the round is a tie, and constructs a descriptive
 * {@link Result} object.
 */
public class ResultEvaluator {

    /**
     * Evaluates a single round of Rock-Paper-Scissors.
     *
     * @param player1Move The move made by player 1. Cannot be null.
     * @param player2Move The move made by player 2. Cannot be null.
     * @param player1Name The name of player 1. Cannot be null.
     * @param player2Name The name of player 2. Cannot be null.
     * @return A {@link Result} object containing the moves, outcome, and a description.
     * @throws NullPointerException if any of the parameters are null.
     */
    public Result evaluate(Move player1Move, Move player2Move, String player1Name, String player2Name) {
        Objects.requireNonNull(player1Move);
        Objects.requireNonNull(player2Move);
        Objects.requireNonNull(player1Name); // Name of player 1
        Objects.requireNonNull(player2Name); // Name of player 2

        if (player1Move == player2Move) {
            return new Result(player1Move, player2Move, Result.Outcome.TIE,
                    String.format("It's a Tie! Both %s and %s chose %s.", player1Name, player2Name, player1Move));
        } else if (player1Move.beats(player2Move)) {
            // Player 1 wins
            return new Result(player1Move, player2Move, Result.Outcome.PLAYER1_WIN,
                    String.format("%s wins! %s %s %s.", player1Name, player1Move, moveAction(player1Move, player2Move), player2Move));
        } else {
            // Player 2 wins
            return new Result(player1Move, player2Move, Result.Outcome.PLAYER2_WIN,
                    String.format("%s wins! %s %s %s.", player2Name, player2Move, moveAction(player2Move, player1Move), player1Move));
        }
    }

    /**
     * Helper method to get the action string (e.g., "crushes", "covers", "cuts")
     * describing how the winning move beats the losing move.
     * This can be expanded if more moves are added.
     *
     * @param winner The winning move.
     * @param loser The losing move.
     * @return A string describing the action (e.g., "crushes"). Returns "beats" as a fallback.
     */
    private String moveAction(Move winner, Move loser) {
        if (winner == Move.ROCK && loser == Move.SCISSORS) return "crushes";
        if (winner == Move.PAPER && loser == Move.ROCK) return "covers";
        if (winner == Move.SCISSORS && loser == Move.PAPER) return "cuts";
        // Fallback for any other combination (though for standard RPS, this shouldn't be hit if beats() is correct)
        return "beats";
    }
}
