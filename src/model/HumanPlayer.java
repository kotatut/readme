package com.example.rps.model;

import com.example.rps.ui.InputHandler;
import com.example.rps.ui.OutputHandler;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a human player in the Rock-Paper-Scissors game.
 * This player makes moves based on user input via the console.
 */
public class HumanPlayer implements Player {
    private static final Logger LOGGER = Logger.getLogger(HumanPlayer.class.getName());
    private final String name;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    /**
     * Constructs a new HumanPlayer.
     *
     * @param name The name of the human player. Cannot be null.
     * @param inputHandler The handler for reading user input. Cannot be null.
     * @param outputHandler The handler for displaying messages to the user. Cannot be null.
     * @throws NullPointerException if name, inputHandler, or outputHandler is null.
     */
    public HumanPlayer(String name, InputHandler inputHandler, OutputHandler outputHandler) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.inputHandler = Objects.requireNonNull(inputHandler, "inputHandler cannot be null");
        this.outputHandler = Objects.requireNonNull(outputHandler, "outputHandler cannot be null");
    }

    /**
     * Prompts the user to enter their move (Rock, Paper, or Scissors) via the console
     * and parses the input. This method will continuously prompt until a valid move is entered.
     *
     * @return The {@link Move} chosen by the human player.
     */
    @Override
    public Move getMove() {
        Optional<Move> move = Optional.empty();
        while (!move.isPresent()) {
            String prompt = name + ", enter your move (Rock[R] " + Move.ROCK.getEmoji() +
                            ", Paper[P] " + Move.PAPER.getEmoji() +
                            ", Scissors[S] " + Move.SCISSORS.getEmoji() + "): ";
            outputHandler.displayMessage(prompt);
            String input = inputHandler.readUserInput();
            move = Move.fromString(input);
            if (!move.isPresent()) {
                LOGGER.log(Level.WARNING, "Invalid move entered by {0}: {1}", new Object[]{name, input});
                outputHandler.displayMessage("Invalid move. Please try again.");
            }
        }
        return move.get();
    }

    /**
     * Gets the name of this human player.
     *
     * @return The player's name.
     */
    @Override
    public String getName() {
        return name;
    }
}
