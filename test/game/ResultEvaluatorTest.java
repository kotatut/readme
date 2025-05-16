package com.example.rps.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.rps.model.Move;
// Result.Outcome is an inner enum, so it's typically imported via Result or used as Result.Outcome
// import com.example.rps.game.Result.Outcome; // This would be correct if Result is already compiled and accessible

public class ResultEvaluatorTest {
    private ResultEvaluator evaluator;
    private final String P1_NAME = "Player1";
    private final String P2_NAME = "Player2";

    @BeforeEach
    void setUp() {
        evaluator = new ResultEvaluator();
    }

    // Tie scenarios
    @Test
    void testRockVsRock_shouldBeTie() {
        Result result = evaluator.evaluate(Move.ROCK, Move.ROCK, P1_NAME, P2_NAME);
        assertEquals(Result.Outcome.TIE, result.outcome());
        assertEquals("It's a Tie! Both " + P1_NAME + " and " + P2_NAME + " chose ROCK.", result.description());
    }

    @Test
    void testPaperVsPaper_shouldBeTie() {
        Result result = evaluator.evaluate(Move.PAPER, Move.PAPER, P1_NAME, P2_NAME);
        assertEquals(Result.Outcome.TIE, result.outcome());
        assertEquals("It's a Tie! Both " + P1_NAME + " and " + P2_NAME + " chose PAPER.", result.description());
    }

    @Test
    void testScissorsVsScissors_shouldBeTie() {
        Result result = evaluator.evaluate(Move.SCISSORS, Move.SCISSORS, P1_NAME, P2_NAME);
        assertEquals(Result.Outcome.TIE, result.outcome());
        assertEquals("It's a Tie! Both " + P1_NAME + " and " + P2_NAME + " chose SCISSORS.", result.description());
    }

    // Player 1 wins scenarios
    @Test
    void testRockBeatsScissors_player1Wins() {
        Result result = evaluator.evaluate(Move.ROCK, Move.SCISSORS, P1_NAME, P2_NAME);
        assertEquals(Result.Outcome.PLAYER1_WIN, result.outcome());
        assertEquals(P1_NAME + " wins! ROCK crushes SCISSORS.", result.description());
    }

    @Test
    void testScissorsBeatsPaper_player1Wins() {
        Result result = evaluator.evaluate(Move.SCISSORS, Move.PAPER, P1_NAME, P2_NAME);
        assertEquals(Result.Outcome.PLAYER1_WIN, result.outcome());
        assertEquals(P1_NAME + " wins! SCISSORS cuts PAPER.", result.description());
    }

    @Test
    void testPaperBeatsRock_player1Wins() {
        Result result = evaluator.evaluate(Move.PAPER, Move.ROCK, P1_NAME, P2_NAME);
        assertEquals(Result.Outcome.PLAYER1_WIN, result.outcome());
        assertEquals(P1_NAME + " wins! PAPER covers ROCK.", result.description());
    }

    // Player 2 wins scenarios (Player 1 loses)
    @Test
    void testRockLosesToPaper_player2Wins() {
        Result result = evaluator.evaluate(Move.ROCK, Move.PAPER, P1_NAME, P2_NAME);
        assertEquals(Result.Outcome.PLAYER2_WIN, result.outcome());
        assertEquals(P2_NAME + " wins! PAPER covers ROCK.", result.description());
    }

    @Test
    void testPaperLosesToScissors_player2Wins() {
        Result result = evaluator.evaluate(Move.PAPER, Move.SCISSORS, P1_NAME, P2_NAME);
        assertEquals(Result.Outcome.PLAYER2_WIN, result.outcome());
        assertEquals(P2_NAME + " wins! SCISSORS cuts PAPER.", result.description());
    }

    @Test
    void testScissorsLosesToRock_player2Wins() {
        Result result = evaluator.evaluate(Move.SCISSORS, Move.ROCK, P1_NAME, P2_NAME);
        assertEquals(Result.Outcome.PLAYER2_WIN, result.outcome());
        assertEquals(P2_NAME + " wins! ROCK crushes SCISSORS.", result.description());
    }
}
