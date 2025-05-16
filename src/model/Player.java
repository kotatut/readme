package com.example.rps.model;

/**
 * Represents a player in the Rock-Paper-Scissors game.
 * A player can be a human or a computer.
 */
public interface Player {
    /**
     * Gets the player's next move.
     *
     * @return The {@link Move} chosen by the player.
     */
    Move getMove();

    /**
     * Gets the name of the player.
     *
     * @return The player's name.
     */
    String getName();
}
