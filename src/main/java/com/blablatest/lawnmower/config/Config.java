package com.blablatest.lawnmower.config;


import java.util.List;

public class Config {
    private final LawnConfig lawnConfig;
    private final List<MowerConfig> mowerConfigs;

    public Config(LawnConfig lawnConfig, List<MowerConfig> mowerConfigs) {
        this.lawnConfig = lawnConfig;
        this.mowerConfigs = mowerConfigs;
    }

    public LawnConfig getLawnConfig() {
        return lawnConfig;
    }

    public List<MowerConfig> getMowerConfigs() {
        return mowerConfigs;
    }


}
