package com.pnc.gamestore.model;

public enum Genre {
    SHOOTER("Action"),
    ADVENTURE("Action-Adventure"),
    RACING("Racing"),
    PUZZLE("Sandbox");

    private final String value;

    Genre(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Genre fromValue(String value) {
        for (Genre g : values()) {
            if (g.value.equals(value)) return g;
        }
        throw new IllegalArgumentException("Unknown genre: " + value);
    }
}