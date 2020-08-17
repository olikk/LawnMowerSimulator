package com.blablatest.lawnmower.config;

import com.blablatest.lawnmower.entities.Command;
import com.blablatest.lawnmower.entities.Coordinate;
import com.blablatest.lawnmower.entities.Direction;

import java.util.List;
import java.util.Objects;

public class MowerConfig {

    private final Direction direction;
    private final Coordinate coordinate;
    private final List<Command> commands;

    public MowerConfig(
            Direction direction,
            Coordinate coordinate,
            List<Command> commands
    ) {
        this.direction = Objects.requireNonNull(direction);
        this.coordinate = Objects.requireNonNull(coordinate);
        this.commands = Objects.requireNonNull(commands);
    }

    public Direction getDirection() {
        return direction;
    }

    public Coordinate getPosition() {
        return coordinate;
    }

    public List<Command> getCommands() {
        return commands;
    }

}
