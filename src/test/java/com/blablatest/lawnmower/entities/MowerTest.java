package com.blablatest.lawnmower.entities;

import com.blablatest.lawnmower.config.LawnConfig;
import com.blablatest.lawnmower.config.MowerConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MowerTest {

    @Test
    void should_throw_exception_when_position_already_occupied(){
        Lawn lawn = new Lawn(new LawnConfig(1,1));
        Mower mower1 = new Mower(
                new MowerConfig(
                        Direction.valueOfLabel("N"),
                        new Coordinate(1,1),
                        Arrays.asList(Command.valueOfLabel("L"))),
                lawn);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> new Mower(
                        new MowerConfig(
                                Direction.valueOfLabel("E"),
                                new Coordinate(1,1),
                                Arrays.asList(Command.valueOfLabel("R"))),
                        lawn), "Exception not thrown");
        assertTrue(thrown.getMessage().contains("already occupied"), "already occupied error message incorrect");
    }

    @Test
    void should_change_direction(){
        Lawn lawn = new Lawn(new LawnConfig(1,1));
        Mower mower = new Mower(
                new MowerConfig(
                        Direction.valueOfLabel("N"),
                        new Coordinate(1,1),
                        Arrays.asList(Command.valueOfLabel("L"))),
                lawn);
        mower.start();
        try {
            mower.getThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertEquals("W", mower.getCurrentDirection().getLabel(), "direction is incorrect");
    }
    @Test
    void should_change_position(){
        Lawn lawn = new Lawn(new LawnConfig(1,1));
        Mower mower = new Mower(
                new MowerConfig(
                        Direction.valueOfLabel("N"),
                        new Coordinate(0,0),
                        Arrays.asList(Command.valueOfLabel("F"))),
                lawn);
        mower.start();
        try {
            mower.getThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assertions.assertAll("Mower new position is incorrect",
                () -> assertEquals(mower.getCurrentPosition().getX(), 0, "X value is incorrect"),
                () -> assertEquals(mower.getCurrentPosition().getY(), 1, "Y value is incorrect")
        );
    }

    @Test
    void should_stay_at_the_same_position(){
        Lawn lawn = new Lawn(new LawnConfig(1,1));
        Mower mower = new Mower(
                new MowerConfig(
                        Direction.valueOfLabel("S"),
                        new Coordinate(0,0),
                        Arrays.asList(Command.valueOfLabel("F"))),
                lawn);
        mower.start();
        try {
            mower.getThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assertions.assertAll("Mower new position is incorrect",
                () -> assertEquals(mower.getCurrentPosition().getX(), 0, "X value is incorrect"),
                () -> assertEquals(mower.getCurrentPosition().getY(), 0, "Y value is incorrect")
        );
    }
}