package com.example.rps.model;

import com.example.rps.ui.InputHandler;
import com.example.rps.ui.OutputHandler;
import java.util.Objects;
import java.util.Optional;

public class HumanPlayer implements Player {
    private final String name;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public HumanPlayer(String name, InputHandler inputHandler, OutputHandler outputHandler) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.inputHandler = Objects.requireNonNull(inputHandler, "inputHandler cannot be null");
        this.outputHandler = Objects.requireNonNull(outputHandler, "outputHandler cannot be null");
    }

    @Override
    public Move getMove() {
        Optional<Move> move = Optional.empty();
        while (!move.isPresent()) {
            outputHandler.displayMessage(name + ", enter your move (Rock, Paper, Scissors): ");
            String input = inputHandler.readUserInput();
            move = Move.fromString(input);
            if (!move.isPresent()) {
                outputHandler.displayMessage("Invalid move. Please try again.");
            }
        }
        return move.get();
    }

    @Override
    public String getName() {
        return name;
    }
}
