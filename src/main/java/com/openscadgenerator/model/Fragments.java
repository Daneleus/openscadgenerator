package com.openscadgenerator.model;

public class Fragments {

    public static long VALUE_DEFAULT = 64;

    public static Fragments DEFAULT = new Fragments();

    long value = VALUE_DEFAULT;

    public long getValue() {
        return value;
    }

    public Fragments value(long value) {
        this.value = value;
        return this;
    }
}
