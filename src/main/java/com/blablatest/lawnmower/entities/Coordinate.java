package com.blablatest.lawnmower.entities;

import java.util.Objects;

public final class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        if (x < 0){
            throw new IllegalArgumentException("'x' should be non-negative, but was " + x);
        }
        if (y < 0){
            throw new IllegalArgumentException("'y' should be non-negative, but was " + y);
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Coordinate coordinate = (Coordinate) o;
        return x == coordinate.x &&
                y == coordinate.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
