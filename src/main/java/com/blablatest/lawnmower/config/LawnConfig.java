package com.blablatest.lawnmower.config;

public class LawnConfig {
    private final int maxX;
    private final int maxY;

    public LawnConfig(int maxX, int maxY) {
        if (maxX < 0){
            throw new IllegalArgumentException("'maxX' should non-negative, but was " + maxX);
        }
        if (maxY < 0){
            throw new IllegalArgumentException("'maxY' should non-negative, but was " + maxY);
        }
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
