package com.example.rps.game;

import com.example.rps.model.Move;
import java.util.Objects;

public class ResultEvaluator {

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

    // Helper method to get the action string (e.g., "crushes", "covers", "cuts")
    // This can be expanded if more moves are added.
    private String moveAction(Move winner, Move loser) {
        if (winner == Move.ROCK && loser == Move.SCISSORS) return "crushes";
        if (winner == Move.PAPER && loser == Move.ROCK) return "covers";
        if (winner == Move.SCISSORS && loser == Move.PAPER) return "cuts";
        // Fallback for any other combination (though for standard RPS, this shouldn't be hit if beats() is correct)
        return "beats";
    }
}
