package com.example.rps.model;

import com.example.rps.strategy.MoveStrategy;
import java.util.Objects;

public class ComputerPlayer implements Player {
    private final String name;
    private final MoveStrategy moveStrategy;

    public ComputerPlayer(String name, MoveStrategy moveStrategy) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.moveStrategy = Objects.requireNonNull(moveStrategy, "moveStrategy cannot be null");
    }

    @Override
    public Move getMove() {
        return moveStrategy.getMove();
    }

    @Override
    public String getName() {
        return name;
    }
}
