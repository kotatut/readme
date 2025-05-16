package com.example.rps.strategy;

import com.example.rps.model.Move;
import java.util.Objects;
import java.util.Random;

public class RandomStrategy implements MoveStrategy {
    private final Random random;
    private static final Move[] MOVES = Move.values();

    public RandomStrategy(Random random) {
        this.random = Objects.requireNonNull(random, "random cannot be null");
    }

    @Override
    public Move selectMove() {
        return MOVES[random.nextInt(MOVES.length)];
    }
}
