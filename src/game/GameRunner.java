package com.example.rps.game;

import com.example.rps.model.Player;
import com.example.rps.ui.OutputHandler;
import java.util.Objects;

public class GameRunner {
    private final Player player1;
    private final Player player2;
    private final int numberOfRounds;
    private final OutputHandler outputHandler;
    private final ResultEvaluator resultEvaluator;
    private int player1Score;
    private int player2Score;

    public GameRunner(Player player1, Player player2, int numberOfRounds, OutputHandler outputHandler, ResultEvaluator resultEvaluator) {
        this.player1 = Objects.requireNonNull(player1, "player1 cannot be null");
        this.player2 = Objects.requireNonNull(player2, "player2 cannot be null");
        this.outputHandler = Objects.requireNonNull(outputHandler, "outputHandler cannot be null");
        this.resultEvaluator = Objects.requireNonNull(resultEvaluator, "resultEvaluator cannot be null");

        if (numberOfRounds <= 0) {
            throw new IllegalArgumentException("Number of rounds must be greater than 0.");
        }
        this.numberOfRounds = numberOfRounds;

        this.player1Score = 0;
        this.player2Score = 0;
    }

    public void playGame() {
        outputHandler.displayMessage("Welcome to Rock-Paper-Scissors!");

        for (int round = 1; round <= numberOfRounds; round++) {
            outputHandler.displayMessage("\n--- Round " + round + " of " + numberOfRounds + " ---");

            // Get moves
            var player1Move = player1.getMove();
            outputHandler.displayMessage(player1.getName() + " chose: " + player1Move);
            var player2Move = player2.getMove();
            outputHandler.displayMessage(player2.getName() + " chose: " + player2Move);

            // Evaluate result
            Result roundResult = resultEvaluator.evaluate(player1Move, player2Move, player1.getName(), player2.getName());
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
        outputHandler.displayMessage("Final Scores: " + player1.getName() + " - " + player1Score + ", " + player2.getName() + " - " + player2Score);

        if (player1Score > player2Score) {
            outputHandler.displayMessage(player1.getName() + " is the overall winner!");
        } else if (player2Score > player1Score) {
            outputHandler.displayMessage(player2.getName() + " is the overall winner!");
        } else {
            outputHandler.displayMessage("The game is a Tie!");
        }

        outputHandler.displayMessage("\nThanks for playing!");
    }
}
