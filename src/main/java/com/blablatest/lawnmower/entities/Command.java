package com.blablatest.lawnmower.entities;

import java.util.HashMap;
import java.util.Map;

public enum Command {
    LEFT("L"),
    RIGHT("R"),
    FORWARD("F");

    private final String label;

    Command(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

    private static final Map<String, Command> BY_LABEL = new HashMap<>();

    static {
        for (Command command: values()) {
            BY_LABEL.put(command.label, command);
        }
    }

    public static Command valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
