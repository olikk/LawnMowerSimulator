package com.blablatest.lawnmower.config.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ConfigValidatorTest {

    @Test
    void should_not_validate_lawn_config() {
        String[] tokens = {"2", "2", "2"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> ConfigValidator.validateLawnConfig(tokens),
                "expected IllegalArgumentException"
        );
        assertTrue(thrown.getMessage().contains(Arrays.toString(tokens)));
    }

    @Test
    void should_not_validate_mower_config() {
        String[] tokens = {"W"};
                IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> ConfigValidator.validateMowerConfig(tokens),
                "expected IllegalArgumentException"
                );
        assertTrue(thrown.getMessage().contains(Arrays.toString(tokens)));
    }
}