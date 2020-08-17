package com.blablatest.lawnmower.config;

import com.blablatest.lawnmower.config.utils.ConfigValidator;
import com.blablatest.lawnmower.entities.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ConfigParserTest {
    private static final String INPUT_TEST = "" +
            "7 5\n" +
            "1 2 N\n" +
            "LFLFLFLFF\n";
    ConfigParser parser = new ConfigParser();
    Config config = parser.parse(INPUT_TEST);

    @Test
    void should_parse_lawn_size() {

        //test lawn config
        Assertions.assertAll("Lawn size parsing is incorrect",
                () -> assertEquals(config.getLawnConfig().getMaxX(), 7, "X value is incorrect"),
                () -> assertEquals(config.getLawnConfig().getMaxY(), 5, "Y value is incorrect")
        );
    }

    @Test
    void should_parse_mower_direction() {
        MowerConfig mower = config.getMowerConfigs().get(0);
        assertEquals(mower.getDirection().toString(), "N", "Direction is incorrect");
    }

    @Test
    void should_parse_mower_position() {
        MowerConfig mower = config.getMowerConfigs().get(0);

        Assertions.assertAll("Mower position parsing is incorrect",
                () -> assertEquals(mower.getPosition().getX(), 1, "X value is incorrect"),
                () -> assertEquals(mower.getPosition().getY(), 2, "Y value is incorrect")
        );

    }

    @Test
    void should_parse_mower_instructions() {
        MowerConfig mower = config.getMowerConfigs().get(0);
        String commands = mower.getCommands().stream()
                .map(Command::getLabel)
                .collect(Collectors.joining(""));
        assertEquals(commands, "LFLFLFLFF", "Commands parsing is incorrect");
    }

    @Test
    void should_not_validate_mower_position() {
        String wrongInput = "" +
                "7 5\n" +
                "X Y N\n" +
                "LFLFLFLFF\n";
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> parser.parse(wrongInput),
                "expected IllegalArgumentException"
        );
        assertTrue(thrown.getMessage().contains("X"));
    }

    @Test
    void should_not_validate_mower_instructions() {
        String wrongInput = "" +
                "7 5\n" +
                "X Y N\n" +
                "XFLFLFLFF\n";
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> parser.parse(wrongInput),
                "expected IllegalArgumentException"
        );
        assertTrue(thrown.getMessage().contains("X"));
    }

}