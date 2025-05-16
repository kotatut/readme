package com.example.rps.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue; // Added for output checking

import com.example.rps.model.ComputerPlayer;
import com.example.rps.model.Move;
import com.example.rps.model.Player;
import com.example.rps.strategy.MoveStrategy;
import com.example.rps.strategy.RandomStrategy;
import com.example.rps.ui.OutputHandler;
// ResultEvaluator is already in com.example.rps.game
// List is not directly needed in this test structure.
import java.util.Random;

public class GameRunnerTest {
    private TestOutputHandler outputHandler; // Changed to TestOutputHandler type
    private ResultEvaluator resultEvaluator;
    private Player humanPlayer; // Will be a ComputerPlayer with FixedMoveStrategy for testing
    private Player computerPlayer;

    // A simple stub for OutputHandler to avoid console spam and allow inspection
    static class TestOutputHandler implements OutputHandler {
        private StringBuilder sb = new StringBuilder();
        public void displayMessage(String message) { sb.append(message).append("\n"); }
        public String getOutput() { return sb.toString(); }
        public void clear() { sb.setLength(0); }
    }

    // Helper FixedMoveStrategy (inner class for GameRunnerTest)
    static class FixedMoveStrategy implements MoveStrategy {
        private final Move move;
        public FixedMoveStrategy(Move move) { this.move = move; }
        @Override public Move selectMove() { return move; }
    }

    @BeforeEach
    void setUp() {
        outputHandler = new TestOutputHandler(); // Use the stub
        resultEvaluator = new ResultEvaluator();
        // Players are initialized per test method based on the scenario
    }

    @Test
    void testConstructor_invalidRounds_throwsIllegalArgumentException() {
        Player fixedPlayer1 = new ComputerPlayer("Fixed1", new FixedMoveStrategy(Move.ROCK));
        Player fixedPlayer2 = new ComputerPlayer("Fixed2", new FixedMoveStrategy(Move.SCISSORS));

        assertThrows(IllegalArgumentException.class, () -> {
            new GameRunner(fixedPlayer1, fixedPlayer2, 0, outputHandler, resultEvaluator);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new GameRunner(fixedPlayer1, fixedPlayer2, -1, outputHandler, resultEvaluator);
        });
    }

    @Test
    void testPlayGame_humanWinsAllRounds() {
        // Human always plays ROCK, Computer always plays SCISSORS
        humanPlayer = new ComputerPlayer("Human", new FixedMoveStrategy(Move.ROCK)); // Simulate human with fixed move
        computerPlayer = new ComputerPlayer("Computer", new FixedMoveStrategy(Move.SCISSORS));
        GameRunner gameRunner = new GameRunner(humanPlayer, computerPlayer, 2, outputHandler, resultEvaluator);
        gameRunner.playGame();
        String output = outputHandler.getOutput(); // Direct call to TestOutputHandler method

        // Check final score and winner message
        assertTrue(output.contains("Final Scores: Human - 2, Computer - 0"), "Output: " + output);
        assertTrue(output.contains("Human is the overall winner!"), "Output: " + output);
    }

    @Test
    void testPlayGame_tieGame() {
        humanPlayer = new ComputerPlayer("Human", new FixedMoveStrategy(Move.PAPER));
        computerPlayer = new ComputerPlayer("Computer", new FixedMoveStrategy(Move.PAPER));
        GameRunner gameRunner = new GameRunner(humanPlayer, computerPlayer, 1, outputHandler, resultEvaluator);
        gameRunner.playGame();
        String output = outputHandler.getOutput();

        assertTrue(output.contains("Final Scores: Human - 0, Computer - 0"), "Output: " + output);
        assertTrue(output.contains("The game is a Tie!"), "Output: " + output);
    }

    @Test
    void testPlayGame_computerWinsAllRounds_usingRandomStrategyWithSeed() {
        // Human plays SCISSORS. Computer (seed 1L) plays ROCK, ROCK (wins both against SCISSORS).
        humanPlayer = new ComputerPlayer("Human", new FixedMoveStrategy(Move.SCISSORS));
        // Seed 1L for RandomStrategy: ROCK, ROCK, PAPER...
        computerPlayer = new ComputerPlayer("Computer", new RandomStrategy(new Random(1L)));
        GameRunner gameRunner = new GameRunner(humanPlayer, computerPlayer, 2, outputHandler, resultEvaluator);
        gameRunner.playGame();
        String output = outputHandler.getOutput();

        assertTrue(output.contains("Final Scores: Human - 0, Computer - 2"), "Output: " + output);
        assertTrue(output.contains("Computer is the overall winner!"), "Output: " + output);
    }
}
