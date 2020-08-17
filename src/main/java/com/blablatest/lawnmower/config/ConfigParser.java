package com.blablatest.lawnmower.config;

import com.blablatest.lawnmower.entities.Command;
import com.blablatest.lawnmower.entities.Coordinate;
import com.blablatest.lawnmower.entities.Direction;
import com.blablatest.lawnmower.config.utils.ConfigValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigParser {

    public Config parse(String input) {
        String[] lines = input.split("\n");
        return new Config(getLawnConfig(lines[0]),getMowersConfig(lines));
    }

    private static LawnConfig getLawnConfig(String lawnConfigLine) {
        String[] coordinate = lawnConfigLine.split(" ");
        LawnConfig lawnConfig;
        ConfigValidator.validateLawnConfig(coordinate);
        try {
            lawnConfig = new LawnConfig(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Error: couldn't parse " + Arrays.toString(coordinate) + " into coordinates");
        }
        return lawnConfig;
    }

    private static List<MowerConfig> getMowersConfig(String[] lines) {
        List<MowerConfig> mowers = new ArrayList<>();
        Coordinate coordinate;
        int x, y;
        for (int i = 1; i < lines.length; i += 2){
            String[] mowerConfigTokens = lines[i].split(" ");
            ConfigValidator.validateMowerConfig(mowerConfigTokens);
            try {
                x = Integer.parseInt(mowerConfigTokens[0]);
                y = Integer.parseInt(mowerConfigTokens[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Error: couldn't parse " + Arrays.toString(mowerConfigTokens) + " into coordinates");
            }
            coordinate =  new Coordinate(x,y);
            Direction direction = Direction.valueOfLabel(mowerConfigTokens[2]);
            if (direction == null){
                throw  new IllegalArgumentException(
                        "Error: couldn't parse direction. Expected 'N', 'E', 'W', or 'S', got '" +
                        mowerConfigTokens[3] + "'");
            }
            List<Command> commands = getMowerCommands(lines[i+1]);
            mowers.add(new MowerConfig(direction, coordinate, commands));
        }
        return mowers;
    }

    private static List<Command> getMowerCommands(String line) {
        List<Command> commands = new ArrayList<>();
        String[] tokens = line.split("");
        for (String token : tokens) {
            Command command = Command.valueOfLabel(token);
            if (command == null) {
                throw new IllegalArgumentException(
                        "Error: couldn't parse command. Expected 'L', 'R', 'F' commands, got '" +
                        token + "'");
            }
            commands.add(command);
        }
        return commands;
    }
}
