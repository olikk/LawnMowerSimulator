package com.blablatest.lawnmower.entities;

import com.blablatest.lawnmower.config.LawnConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LawnTest {
    private final static Lawn LAWN = new Lawn(new LawnConfig(0,1));

    @Test
    void should_forbid_to_occupy_position_out_of_bounds() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> LAWN.tryOccupy(new Coordinate(1,1)));
        assertTrue(thrown.getMessage().contains("out of bounds"), "out of bounds error message incorrect");
    }



}