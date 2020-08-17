package com.blablatest.lawnmower.config;

import com.blablatest.lawnmower.entities.Command;
import com.blablatest.lawnmower.entities.Coordinate;
import com.blablatest.lawnmower.entities.Direction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MowerConfigTest {
    private final static MowerConfig MOWER_CONFIG = new MowerConfig(
            Direction.valueOfLabel("N"),
            new Coordinate(0, 0),
            Arrays.asList(Command.valueOfLabel("F"))
            );
    @Test
    void should_get_direction() {
        assertEquals("N", MOWER_CONFIG.getDirection().getLabel());
    }

    @Test
    void should_get_position() {
        assertEquals(0, MOWER_CONFIG.getPosition().getX());
    }

    @Test
    void should_get_commands() {
        assertEquals("F", MOWER_CONFIG.getCommands().get(0).getLabel());
    }
}