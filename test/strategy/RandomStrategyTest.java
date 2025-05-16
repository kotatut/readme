package com.example.rps.strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows; // For NullPointerException test

import com.example.rps.model.Move;
import java.util.Random;
import java.util.Arrays; // For Arrays.asList

public class RandomStrategyTest {

    @Test
    void testSelectMove_returnsValidMove() {
        RandomStrategy randomStrategy = new RandomStrategy(new Random());
        Move selectedMove = randomStrategy.selectMove();
        assertNotNull(selectedMove, "Selected move should not be null");
        assertTrue(Arrays.asList(Move.values()).contains(selectedMove), "Selected move must be one of the defined Moves");
    }

    @Test
    void testSelectMove_usesProvidedRandom_deterministic() {
        // Seed the random number generator to get a predictable sequence
        // Move.values() -> [ROCK, PAPER, SCISSORS]
        // The output of Random(seed).nextInt(n) is consistent for a given Java version.
        // For new Random(1L).nextInt(3):
        // Java 8-17 (and likely others): 0, 0, 1
        // So, first call with seed 1L should be Move.values()[0] -> ROCK.
        // Second call should be Move.values()[0] -> ROCK.
        // Third call should be Move.values()[1] -> PAPER.
        Random predictableRandom = new Random(1L); // Use a fixed seed
        RandomStrategy randomStrategy = new RandomStrategy(predictableRandom);

        assertEquals(Move.ROCK, randomStrategy.selectMove(), "With seed 1L, first move should be ROCK");
        assertEquals(Move.ROCK, randomStrategy.selectMove(), "With seed 1L, second move should also be ROCK");
        assertEquals(Move.PAPER, randomStrategy.selectMove(), "With seed 1L, third move should be PAPER");
    }

    @Test
    void testConstructor_nullRandom_throwsNullPointerException() {
        // Assert that passing a null Random object to the constructor throws a NullPointerException
        assertThrows(NullPointerException.class, () -> {
            new RandomStrategy(null);
        }, "Constructor should throw NullPointerException when Random instance is null");
    }
}
