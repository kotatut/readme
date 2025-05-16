Rock-Paper-Scissors Game Implementation for Senior Backend Engineer Assessment

## Project Overview

The goal of this project is to implement a Rock-Paper-Scissors game in Java. This is an assessment task for a Senior Backend Engineer position. The implementation should not only fulfill the basic requirements but also demonstrate a high level of proficiency in software design, architecture, and best practices.

This document serves as a guide for a sophisticated agentic LLM to generate the Java implementation.

## Core Requirements

1.  **Game Implementation**: Implement the classic Rock-Paper-Scissors game.
    * Rock beats Scissors
    * Scissors beats Paper
    * Paper beats Rock
2.  **Player Modes**: Support Human vs. Computer play.
3.  **Multiple Rounds**: Allow the game to be played for a configurable number of rounds (n times).
4.  **Program Exit**: The program should exit gracefully after the game is completed.

## Senior-Level Expectations

Beyond the core functional requirements, the implementation will be evaluated on the following:

1.  **Code Architecture**: Clarity, separation of concerns, and maintainability of the solution structure.
2.  **Error Handling**: Robustness in handling edge cases, invalid inputs, and unexpected situations.
3.  **Testing**: Comprehensive and meaningful unit and integration tests.
4.  **Design Patterns**: Appropriate and effective use of relevant design patterns.
5.  **Extensibility**: The ease with which the code can be adapted for future requirements.
6.  **Readability & Maintainability**: Code should be simple, easy to understand, and well-structured. Minimal redundancy (DRY principle). No magic numbers or unexplained string literals; use constants or enums.
7.  **Functionality & Robustness**: The program must compile without errors or warnings and run correctly, producing the expected output for various scenarios (wins, losses, ties). It must handle the 'n' rounds requirement correctly and gracefully manage invalid user inputs.

## Architectural Guidance

### 1. Domain Modeling

Create well-defined classes and interfaces that separate concerns. Key areas to model include:

* **Game Logic**: Encapsulates the rules of Rock-Paper-Scissors, determines winners, and manages the game flow.
* **Player Abstraction**:
    * `Player` interface.
    * Concrete implementations for `HumanPlayer` and `ComputerPlayer`.
* **Input/Output (I/O) Handling**:
    * Abstract I/O through interfaces (e.g., `InputHandler`, `OutputHandler`). This allows for flexibility (e.g., console, UI).
* **Game State Management**: Clearly define how the game's state (e.g., scores, current round) is managed. Consider immutability.
* **Move Representation**: `Move` (e.g., an enum for Rock, Paper, Scissors).

### 2. Proposed File Structure

Organize the project using a "package by feature" approach. The following structure is recommended:

src/ ├── Main.java // Application entry point ├── game/ │ ├── GameRunner.java // Orchestrates the game flow │ ├── Result.java // Represents the result of a single round/game │ └── ResultEvaluator.java // Logic to determine the winner of a round ├── model/ │ ├── Move.java // Enum for Rock, Paper, Scissors (and potentially others) │ └── Player.java // Interface for players, HumanPlayer, ComputerPlayer classes ├── strategy/ // For computer player move selection strategies │ ├── MoveStrategy.java // Interface for move selection algorithms │ ├── RandomStrategy.java // Concrete strategy for random move selection │ └── FrequencyAnalysisStrategy.java // (Optional advanced strategy) └── ui/ └── InputHandler.java // Interface/class for handling user input └── OutputHandler.java // Interface/class for displaying output (can be combined with InputHandler if simple)

test/ └── game/ └── ResultEvaluatorTest.java // Example test class // Other test classes for game logic, strategies, player interactions etc.

## Design Patterns to Implement

### 1. Strategy Pattern

* **Usage**: For computer player move selection.
* **Details**:
    * Define a `MoveStrategy` interface (e.g., `Move selectMove();`).
    * Implement concrete strategies:
        * `RandomMoveStrategy`: Selects moves randomly.
        * (Optional) More advanced strategies like `FrequencyAnalysisStrategy` or a strategy that always picks a specific move.
* **Benefits**: Decouples the computer player from its move selection algorithm, allowing strategies to be switched easily (even at runtime if desired) and new ones added without modifying the `ComputerPlayer` class.

### 2. Dependency Injection (DI)

* **Usage**: Throughout the application, especially in constructors (constructor injection).
* **Details**:
    * Pass dependencies (e.g., `MoveStrategy` into `ComputerPlayer`, `InputHandler`/`OutputHandler` into `GameRunner`) via constructors.
    * Declare dependencies as `final` fields where appropriate.
    * Use `Objects.requireNonNull()` for null checks on injected dependencies in constructors.
* **Benefits**: Enhances testability (allows mocking), makes dependencies explicit, and reduces coupling.

### 3. Command Pattern (Implicit)

* **Usage**: Game flow orchestration within `GameRunner`.
* **Details**: The `GameRunner.start()` method (or similar) encapsulates the sequence of operations to play the game. It decouples the game setup and initiation from the actual step-by-step execution logic.
* **Benefits**: Encapsulates game execution logic and separates the "what" from the "how".

### 4. Immutable Object Pattern

* **Usage**: For domain models like `GameState` (if created to track scores, rounds) and `GameResult`.
* **Details**:
    * Use Java Records (Java 16+) for concise immutable data objects.
    * Ensure that once an object is created, its state cannot be changed. Updates should produce new instances.
    * Perform validation in constructors (e.g., compact constructors in records).
* **Benefits**: Thread safety, predictability, easier debugging, and simplified equality checking.

## Java-Specific Best Practices (Target Java 17+)

### 1. Records (Java 16+)

* **Usage**: For Plain Old Java Objects (POJOs) primarily used as data carriers, like `GameResult`, `GameState`, or simple configuration objects.
* **Benefits**: Concise syntax, automatic `equals()`, `hashCode()`, `toString()`, and getters; built-in immutability.

### 2. Switch Expressions (Java 14+)

* **Usage**: For determining game results (e.g., in `ResultEvaluator`) or parsing `Move` from string input.
* **Benefits**: More readable and less error-prone than traditional switch statements (e.g., no fall-through bugs), can return values directly, compiler checks for exhaustiveness (for enums).

### 3. `Optional` for Nullable Returns (Java 8+)

* **Usage**: For methods that might not return a value, e.g., `Move.fromString(String input)` if the input is invalid.
* **Benefits**: Makes the potential absence of a value explicit in the method signature, forces callers to handle the "empty" case, helps prevent `NullPointerExceptions`.

## Software Engineering Best Practices

### 1. Interface Segregation Principle (ISP)

* **Usage**: For I/O handling.
* **Details**: Define separate, focused interfaces like `InputHandler` (e.g., `String readUserInput()`) and `OutputHandler` (e.g., `void displayMessage(String message)`).
* **Benefits**: Clients only depend on methods they use. Promotes flexibility and testability.

### 2. Defensive Programming

* **Usage**: Throughout the codebase.
* **Details**:
    * Validate parameters in public methods and constructors (fail fast).
    * Check for invalid game states or inputs.
    * Implement robust error handling with clear, user-friendly messages.
    * Handle potential exceptions gracefully (e.g., `IOException` from input, `NumberFormatException` for round input).
* **Benefits**: Increases application stability and provides better feedback.

### 3. Command-Query Separation (CQS)

* **Usage**: In method design, especially within domain/service logic.
* **Details**: Methods should either be commands (perform an action, potentially mutating state, typically void or returning `this`) or queries (return data, no side effects).
    * Example: A method to play a round (command) might update the game state. A method to get the current score (query) should not change anything.
    * If using immutable state objects, "update" methods will be queries that return a *new* state object.
* **Benefits**: Makes code easier to reason about and test.

### 4. Package by Feature

* **Usage**: For the overall project structure (as outlined in "Proposed File Structure").
* **Benefits**: High cohesion within packages, better encapsulation, clearer dependencies between features.

## Key Features & Configuration

### 1. Configuration and Parameterization

* **Number of Rounds**: Allow the user to specify the number of rounds to play (e.g., via command-line argument or prompt at startup).
* **Win Conditions**: The game ends after 'n' rounds. The player with the most wins is the overall winner.
* **(Optional) Difficulty Levels**: If implementing multiple computer strategies, allow selection of difficulty (could map to different `MoveStrategy` implementations).

### 2. Input/Output

* **User Input**: Gracefully handle user input for moves (e.g., "rock", "paper", "scissors", or abbreviations). Validate input.
* **Game Output**: Clearly display:
    * Round number.
    * Choices made by human and computer.
    * Outcome of each round (Win/Loss/Tie for the human player).
    * Running scores.
    * Final game result.

## Quality Assurance

### 1. Testing

* **Unit Tests**:
    * Test core game logic (`ResultEvaluator` for all move combinations).
    * Test player move generation (especially `MoveStrategy` implementations).
    * Test input validation and parsing.
    * Ensure `RandomMoveStrategy` can accept a `java.util.Random` instance for deterministic testing.
* **Integration Tests**:
    * Test the `GameRunner` flow for a few rounds.
* **Testability**:
    * Design with interfaces for easy mocking.
    * Favor pure functions (no side effects) for game logic where possible.
    * Leverage immutable state objects for predictable test assertions.
* **Coverage**: Aim for adequate test coverage for the complexity of the problem. Tests should be readable and maintainable.

### 2. Input Validation

* Validate user input for moves (e.g., handle incorrect spellings, invalid choices).
* Validate input for the number of rounds (e.g., must be a positive integer).

### 3. Logging

* Implement basic logging (e.g., using `java.util.logging` or a simple library like SLF4J with a basic binding) for important game events or errors. This is more for troubleshooting than for user display.

### 4. Error Messages

* Provide clear and informative error messages to the user for invalid inputs or other issues.

## Extensibility (Design for Future Enhancements)

Design the system with the following potential extensions in mind:

1.  **New Game Variants**:
    * Easily add new move types (e.g., Rock-Paper-Scissors-Lizard-Spock). This might involve modifying the `Move` enum and updating the `ResultEvaluator`.
    * Support different rule sets by potentially having different `ResultEvaluator` implementations or a more flexible rule engine.
2.  **Alternative UI Implementations**:
    * The use of `InputHandler` and `OutputHandler` interfaces should make it possible to swap the console UI for a GUI or web interface without changing core game logic.
3.  **Advanced Computer Players**:
    * The `MoveStrategy` pattern directly supports adding new, more sophisticated AI strategies.
4.  **Multiplayer Support**:
    * While not a core requirement, consider how the `Player` interface and game flow might be extended to support multiple human players (local or network). This is a thought exercise for design.
5.  **Persistence of Game Statistics**:
    * How could game history or player statistics be saved and loaded? (Design consideration, not for implementation unless explicitly requested).

## Documentation (To be generated alongside the code)

* **In-code comments**: For complex logic or public APIs (`javadoc` style).
* **This README.md**: Will serve as the primary design document.
* **Setup and Usage Instructions**: Include brief instructions on how to compile and run the game.

By following these guidelines, the generated Java application will be a robust, well-engineered solution that effectively demonstrates senior-level backend development skills.
