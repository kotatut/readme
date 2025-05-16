package com.example.rps.strategy;

import com.example.rps.model.Move;

/**
 * Defines the interface for a strategy that a computer player can use
 * to select its next move in the Rock-Paper-Scissors game.
 */
public interface MoveStrategy {
    /**
     * Selects a move based on the implementing strategy.
     *
     * @return The {@link Move} selected by the strategy.
     */
    Move selectMove();
}
