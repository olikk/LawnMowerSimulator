package com.blablatest.lawnmower.config.utils;

import java.util.Arrays;

public class ConfigValidator {
    private ConfigValidator() {}

    public static void validateLawnConfig(String[] tokens) {
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Error: wrong Lawn size config. Expected 'X' 'Y', got: " +
                    Arrays.toString(tokens));
        }
    }

    public static void validateMowerConfig(String[] tokens) {
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Error: wrong Mower config. Expected 'X' 'Y' 'O', got: " +
                    Arrays.toString(tokens));
        }
    }
}
