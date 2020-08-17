package com.blablatest.lawnmower.entities;

import com.blablatest.lawnmower.config.LawnConfig;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Lawn {
    private final Map<Coordinate, Cell> cells = new HashMap<>();

    public Lawn(LawnConfig lawnConfig) {
        /* transform the lawn to the grid */
        for (int x = 0; x <= lawnConfig.getMaxX(); x++) {
            for (int y = 0; y <= lawnConfig.getMaxY(); y++) {
                cells.put(new Coordinate(x, y), new Cell());
            }
        }
    }

    /**
     * Validates the initial mower position
     * @param coordinate initial mower coordinates
     * @return Position if the position is valid and not occupied
     *         null otherwise
     */
    public Position tryOccupy(Coordinate coordinate) {
        Cell cell = cells.get(coordinate);
        if (cell == null) {
            throw new IllegalArgumentException(coordinate + " is out of bounds");
        }
        synchronized (cell) {
            if (cell.isOccupied) {
                return null;
            } else {
                cell.isOccupied = true;
                return new Position(coordinate);
            }
        }
    }

    private static final class Cell {
        private boolean isOccupied = false;
    }

    private static final Comparator<Coordinate> COORDINATE_COMPARATOR = (c1, c2) -> {
        if (c1.getY() == c2.getY()) {
            return Integer.compare(c1.getX(), c2.getX());
        } else {
            return Integer.compare(c1.getY(), c2.getY());
        }
    };

    public final class Position {
        private volatile Coordinate coordinate;

        public Position(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        public Coordinate coordinate() {
            return coordinate;
        }

        /**
         * Allows (or not) a mower to change the position
         * @param newCoordinate coordinate of target position
         * @return true if the position is valid and not occupied
         *         false otherwise
         */
        public boolean tryMove(Coordinate newCoordinate) {
            Cell newCell = cells.get(newCoordinate);
            if (newCell == null) {
                // coordinate out of bounds
                return false;
            }
            Cell oldCell = cells.get(coordinate);
            // Setting the absolute order on all coordinates to prevent dead lock
            Coordinate lowerCoordinate = COORDINATE_COMPARATOR.compare(coordinate, newCoordinate) <= 0 ?
                    coordinate :
                    newCoordinate;
            Coordinate higherCoordinate = coordinate.equals(lowerCoordinate) ?
                    newCoordinate :
                    coordinate;

            Cell lowerCell = cells.get(lowerCoordinate);
            Cell higherCell = cells.get(higherCoordinate);
            /* block two cells in order to avoid eventual errors */
            synchronized (lowerCell) {
                synchronized (higherCell) {
                    if (newCell.isOccupied) {
                        return false;
                    } else {
                        newCell.isOccupied = true;
                        oldCell.isOccupied = false;
                        /* update current mower position */
                        this.coordinate = newCoordinate;
                        return true;
                    }
                }
            }
        }

        public void free() {
            Cell cell = cells.get(coordinate);
            synchronized (cell) {
                cell.isOccupied = false;
            }
        }
    }
}
