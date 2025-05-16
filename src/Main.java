package com.example.rps;

import com.example.rps.game.GameRunner;
import com.example.rps.game.ResultEvaluator;
import com.example.rps.model.ComputerPlayer;
import com.example.rps.model.HumanPlayer;
import com.example.rps.model.Player;
import com.example.rps.strategy.RandomStrategy;
import com.example.rps.ui.ConsoleInputHandler;
import com.example.rps.ui.ConsoleOutputHandler;
import com.example.rps.ui.InputHandler;
import com.example.rps.ui.OutputHandler;
import java.util.Random;
// Scanner is not directly needed here as ConsoleInputHandler manages its own.

public class Main {
    public static void main(String[] args) {
        // 1. Initialize Core Components
        OutputHandler outputHandler = new ConsoleOutputHandler();
        InputHandler inputHandler = new ConsoleInputHandler(); // ConsoleInputHandler creates its own Scanner
        ResultEvaluator resultEvaluator = new ResultEvaluator();

        // 2. Display Welcome Message
        outputHandler.displayMessage("=========================================");
        outputHandler.displayMessage("   Welcome to Rock, Paper, Scissors!   ");
        outputHandler.displayMessage("=========================================");
        outputHandler.displayMessage(""); // For spacing

        // 3. Get Number of Rounds
        int numberOfRounds = 0;
        while (numberOfRounds <= 0) {
            numberOfRounds = inputHandler.readInt("Enter the number of rounds to play: ");
            if (numberOfRounds <= 0) {
                outputHandler.displayMessage("Please enter a positive number for rounds.");
            }
        }
        outputHandler.displayMessage(""); // For spacing

        // 4. Initialize Players
        // Optional: Allow user to enter their name for HumanPlayer
        // For simplicity, using a fixed name "Human".
        // String playerName = inputHandler.readUserInput("Enter your name: "); // Example if we wanted name input
        // Player humanPlayer = new HumanPlayer(playerName, inputHandler, outputHandler);
        Player humanPlayer = new HumanPlayer("Human", inputHandler, outputHandler);
        Player computerPlayer = new ComputerPlayer("Computer", new RandomStrategy(new Random()));

        outputHandler.displayMessage("Players are ready: " + humanPlayer.getName() + " vs " + computerPlayer.getName());
        outputHandler.displayMessage(""); // For spacing

        // 5. Initialize GameRunner
        GameRunner gameRunner = new GameRunner(humanPlayer, computerPlayer, numberOfRounds, outputHandler, resultEvaluator);

        // 6. Start Game
        gameRunner.playGame();

        // 7. Display Exit Message
        outputHandler.displayMessage("\nThank you for playing! Goodbye.");
        outputHandler.displayMessage("=========================================");

        // 8. (Optional but good practice) Close resources
        // If ConsoleInputHandler or other components used resources that need explicit closing,
        // they would be closed here. For a Scanner on System.in, it's generally not closed.
        // e.g., if inputHandler had a close() method:
        // if (inputHandler instanceof ConsoleInputHandler) {
        //     ((ConsoleInputHandler) inputHandler).close();
        // }
    }
}
