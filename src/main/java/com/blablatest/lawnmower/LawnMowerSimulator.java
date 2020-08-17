package com.blablatest.lawnmower;

import com.blablatest.lawnmower.config.Config;
import com.blablatest.lawnmower.config.ConfigParser;
import com.blablatest.lawnmower.config.MowerConfig;
import com.blablatest.lawnmower.entities.Lawn;
import com.blablatest.lawnmower.entities.Mower;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LawnMowerSimulator {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: LawnMowerSimulator <file.txt>");
            return;
        }
        String path = args[0];
        String input = "";
        try{
            input = Files.readString(Path.of(path));
        } catch (IOException ioe){
            System.out.println("When opening the file I/O error occurred: "+ioe);
            return;
        }
        ConfigParser parser = new ConfigParser();
        Config config = parser.parse(input);

        Lawn lawn = new Lawn(config.getLawnConfig());

        List<Mower> mowers = new ArrayList<>();
        for (MowerConfig mowerConfig : config.getMowerConfigs()) {
            mowers.add(new Mower(mowerConfig, lawn));
        }
        mowers.forEach(Mower::start);
        // wait for all threads to finish
        for (Mower mower : mowers) {
            try {
                mower.getThread().join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
