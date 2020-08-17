package com.blablatest.lawnmower.entities;

import com.blablatest.lawnmower.config.MowerConfig;

import java.util.List;
import java.util.logging.Logger;

public class Mower {
    private static final Logger LOGGER = Logger.getLogger(Mower.class.getName());
    private volatile Direction direction;
    private volatile Lawn.Position position;
    private final List<Command> commands;
    private final Thread thread;

    public Mower(MowerConfig config, Lawn lawn) {
        this.direction = config.getDirection();
        this.commands = config.getCommands();
        Coordinate initialPosition = config.getPosition();
        this.position = lawn.tryOccupy(initialPosition);
        if (this.position == null) {
            throw new IllegalArgumentException("Position " + initialPosition + " is already occupied");
        }
        this.thread = new Thread(this::run);
    }

    private void run() {
        for (Command command : commands) {
            switch (command) {
                default:
                    throw new UnsupportedOperationException(command + " command is not supported");
                case LEFT:
                    // safe as accessed only from `thread`
                    this.direction = direction.left();
                    break;
                case RIGHT:
                    // safe as accessed only from `thread`
                    this.direction = direction.right();
                    break;
                case FORWARD:
                    Coordinate currentPosition = position.coordinate();
                    int newX = currentPosition.getX();
                    int newY = currentPosition.getY();
                    switch (direction) {
                        default:
                            throw new UnsupportedOperationException(command + " command is not supported");
                        case EAST:
                            newX++; break;
                        case NORTH:
                            newY++; break;
                        case WEST:
                            newX--; break;
                        case SOUTH:
                            newY--; break;
                    }
                    if (newX >= 0 && newY >= 0) {
                        position.tryMove(new Coordinate(newX, newY));
                    }
                    LOGGER.fine("New Position: " + position.coordinate().toString());
                    break;
            }
        }
        Coordinate currentPosition = this.getCurrentPosition();
        System.out.println(currentPosition.getX() + " " + currentPosition.getY() + " " + this.getCurrentDirection());
    }

    public void start(){
        thread.start();
        LOGGER.fine("New Thread started");
    }

    public Direction getCurrentDirection(){
        return direction;
    }

    public Coordinate getCurrentPosition(){
        return position.coordinate();
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public String toString() {
        return position.coordinate().toString() + " " + direction.toString();
    }
}
