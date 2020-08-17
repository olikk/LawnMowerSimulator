package com.blablatest.lawnmower;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class LawnMowerSimulatorTest {

    @Test
    public void should_show_usage_when_insufficient_arguments_supplied() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream console = System.out;
        String[] args = new String[]{};
        try {
            System.setOut(new PrintStream(bytes));  //change default output
            LawnMowerSimulator.main(args);
        } finally {
            System.setOut(console);
        }
        assertEquals("Usage: LawnMowerSimulator <file.txt>\n",
                bytes.toString(), "Usage message is incorrect");
    }

    @Test
    void should_execute_simulation() {

        String[] args = new String[]{"src/test/resources/lawnmower-config.txt"};
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream console = System.out;
        try {
            System.setOut(new PrintStream(bytes));
            LawnMowerSimulator.main(args);
        } finally {
            System.setOut(console);
        }
        assertTrue(bytes.toString().contains("1 3 N") && bytes.toString().contains("5 1 E"));
    }

    @Test
    public void should_launch_many_mowers_simulation() {
        String[] args = new String[]{"src/test/resources/lawnmower-many-mowers.txt"};
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream console = System.out;
        try {
            System.setOut(new PrintStream(bytes));
            LawnMowerSimulator.main(args);
        } finally {
            System.setOut(console);
        }
        assertEquals(bytes.toString().split("\n").length, 20);
    }

    @Test
    public void should_escape_deadlock() {
        String[] args = new String[]{"src/test/resources/lawnmower-deadlock-test-config.txt"};
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream console = System.out;
        try {
            System.setOut(new PrintStream(bytes));
            LawnMowerSimulator.main(args);
        } finally {
            System.setOut(console);
        }
        assertEquals(bytes.toString().split("\n").length, 2);
    }

}