package com.example.rps.model;

import com.example.rps.strategy.MoveStrategy;
import java.util.Objects;

/**
 * Represents a computer player in the Rock-Paper-Scissors game.
 * This player uses a {@link MoveStrategy} to determine its next move.
 */
public class ComputerPlayer implements Player {
    private final String name;
    private final MoveStrategy moveStrategy;

    /**
     * Constructs a new ComputerPlayer.
     *
     * @param name The name of the computer player. Cannot be null.
     * @param moveStrategy The strategy this player will use to select moves. Cannot be null.
     * @throws NullPointerException if name or moveStrategy is null.
     */
    public ComputerPlayer(String name, MoveStrategy moveStrategy) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.moveStrategy = Objects.requireNonNull(moveStrategy, "moveStrategy cannot be null");
    }

    /**
     * Selects a move based on the configured {@link MoveStrategy}.
     *
     * @return The {@link Move} chosen by the computer player's strategy.
     */
    @Override
    public Move getMove() {
        return moveStrategy.selectMove(); // Corrected to call selectMove() on the strategy
    }

    /**
     * Gets the name of this computer player.
     *
     * @return The player's name.
     */
    @Override
    public String getName() {
        return name;
    }
}
