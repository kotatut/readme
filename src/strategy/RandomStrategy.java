package com.example.rps.strategy;

import com.example.rps.model.Move;
import java.util.Objects;
import java.util.Random;

/**
 * A {@link MoveStrategy} that selects a move randomly from the available {@link Move} options.
 */
public class RandomStrategy implements MoveStrategy {
    private final Random random;
    private static final Move[] MOVES = Move.values();

    /**
     * Constructs a new RandomStrategy.
     *
     * @param random The {@link Random} instance to use for selecting moves. Cannot be null.
     * @throws NullPointerException if random is null.
     */
    public RandomStrategy(Random random) {
        this.random = Objects.requireNonNull(random, "random cannot be null");
    }

    /**
     * Selects a {@link Move} randomly.
     *
     * @return A randomly selected {@link Move}.
     */
    @Override
    public Move selectMove() {
        return MOVES[random.nextInt(MOVES.length)];
    }
}
