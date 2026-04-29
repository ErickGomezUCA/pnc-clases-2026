package com.pnc.gamestore.model;

public enum Classification {
    E("E"),
    E_PLUS_10("E10+"),
    T("T"),
    M("M");

    private final String value;

    Classification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Classification fromValue(String value) {
        for (Classification c : values()) {
            if (c.value.equals(value)) return c;
        }
        throw new IllegalArgumentException("Unknown classification: " + value);
    }
}