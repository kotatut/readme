package com.example.rps.game;

import com.example.rps.model.Player;
import com.example.rps.ui.OutputHandler;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Orchestrates the Rock-Paper-Scissors game flow for a specified number of rounds.
 * Manages player interactions, scoring, and round evaluation.
 */
public class GameRunner {
    private static final Logger LOGGER = Logger.getLogger(GameRunner.class.getName());
    private final Player player1;
    private final Player player2;
    private final int numberOfRounds;
    private final OutputHandler outputHandler;
    private final ResultEvaluator resultEvaluator;
    private int player1Score;
    private int player2Score;

    /**
     * Constructs a new GameRunner.
     *
     * @param player1 The first player.
     * @param player2 The second player.
     * @param numberOfRounds The total number of rounds to be played. Must be positive.
     * @param outputHandler Handles displaying messages to the user.
     * @param resultEvaluator Logic to determine the winner of each round.
     * @throws IllegalArgumentException if numberOfRounds is not positive.
     */
    public GameRunner(Player player1, Player player2, int numberOfRounds, OutputHandler outputHandler, ResultEvaluator resultEvaluator) {
        this.player1 = Objects.requireNonNull(player1, "player1 cannot be null");
        this.player2 = Objects.requireNonNull(player2, "player2 cannot be null");
        this.outputHandler = Objects.requireNonNull(outputHandler, "outputHandler cannot be null");
        this.resultEvaluator = Objects.requireNonNull(resultEvaluator, "resultEvaluator cannot be null");

        if (numberOfRounds <= 0) {
            String errorMessage = "Number of rounds must be greater than 0. Received: " + numberOfRounds;
            LOGGER.log(Level.SEVERE, errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        this.numberOfRounds = numberOfRounds;

        this.player1Score = 0;
        this.player2Score = 0;
    }

    /**
     * Starts and manages the game play for all rounds.
     * Displays round information, scores, and the final game outcome.
     */
    public void playGame() {
        LOGGER.log(Level.INFO, "playGame started. Player1: {0}, Player2: {1}, Rounds: {2}", new Object[]{player1.getName(), player2.getName(), numberOfRounds});
        outputHandler.displayMessage("Welcome to Rock-Paper-Scissors!");

        for (int round = 1; round <= numberOfRounds; round++) {
            LOGGER.log(Level.INFO, "Starting Round {0} of {1}", new Object[]{round, numberOfRounds});
            outputHandler.displayMessage("\n--- Round " + round + " of " + numberOfRounds + " ---");

            // Get moves
            var player1Move = player1.getMove();
            LOGGER.log(Level.FINE, "Player {0} chose: {1}", new Object[]{player1.getName(), player1Move});
            outputHandler.displayMessage(player1.getName() + " chose: " + player1Move);
            var player2Move = player2.getMove();
            LOGGER.log(Level.FINE, "Player {0} chose: {1}", new Object[]{player2.getName(), player2Move});
            outputHandler.displayMessage(player2.getName() + " chose: " + player2Move);

            // Evaluate result
            Result roundResult = resultEvaluator.evaluate(player1Move, player2Move, player1.getName(), player2.getName());
            LOGGER.log(Level.INFO, "Round {0} result: {1}", new Object[]{round, roundResult.description()});
            outputHandler.displayMessage(roundResult.description());

            // Update scores
            switch (roundResult.outcome()) {
                case PLAYER1_WIN:
                    player1Score++;
                    break;
                case PLAYER2_WIN:
                    player2Score++;
                    break;
                case TIE:
                    // No score change for a tie
                    break;
            }
            outputHandler.displayMessage("Scores: " + player1.getName() + " - " + player1Score + ", " + player2.getName() + " - " + player2Score);
        }

        // Display final results
        outputHandler.displayMessage("\n--- Game Over ---");
        LOGGER.log(Level.INFO, "Final Scores: {0} - {1}, {2} - {3}", new Object[]{player1.getName(), player1Score, player2.getName(), player2Score});
        outputHandler.displayMessage("Final Scores: " + player1.getName() + " - " + player1Score + ", " + player2.getName() + " - " + player2Score);

        if (player1Score > player2Score) {
            LOGGER.log(Level.INFO, "Overall Winner: {0}", player1.getName());
            outputHandler.displayMessage(player1.getName() + " is the overall winner!");
        } else if (player2Score > player1Score) {
            LOGGER.log(Level.INFO, "Overall Winner: {0}", player2.getName());
            outputHandler.displayMessage(player2.getName() + " is the overall winner!");
        } else {
            LOGGER.log(Level.INFO, "Game ended in a Tie.");
            outputHandler.displayMessage("The game is a Tie!");
        }

        outputHandler.displayMessage("\nThanks for playing!");
        LOGGER.log(Level.INFO, "playGame finished.");
    }
}
