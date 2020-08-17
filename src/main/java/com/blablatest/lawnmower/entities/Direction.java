package com.blablatest.lawnmower.entities;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    NORTH("N"){
        @Override
        public Direction left() {
            return Direction.WEST;
        }
        @Override
        public Direction right() {
            return Direction.EAST;
        }
    },
    EAST("E"){
        @Override
        public Direction left() {
            return Direction.NORTH;
        }
        @Override
        public Direction right() {
            return Direction.SOUTH;
        }
    },
    WEST("W"){
        @Override
        public Direction left() {
            return Direction.SOUTH;
        }
        @Override
        public Direction right() {
            return Direction.NORTH;
        }
    },
    SOUTH("S"){
        @Override
        public Direction left() {
            return Direction.EAST;
        }
        @Override
        public Direction right() {
            return Direction.WEST;
        }
    }
    ;

    private final String label;

    Direction(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
    /* get direction to the left from current */
    public abstract Direction left();
    /* get direction to the right from current */
    public abstract Direction right();

    @Override
    public String toString() {
        return  label;
    }

    private static final Map<String, Direction> BY_LABEL = new HashMap<>();

    static {
        for (Direction direction: values()) {
            BY_LABEL.put(direction.label, direction);
        }
    }

    public static Direction valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
