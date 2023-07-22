package com.openscadgenerator.model;

public class Height {

    public static double VALUE_DEFAULT = 100;

    public static Height DEFAULT = new Height();

    double value = VALUE_DEFAULT;

    public double getValue() {
        return value;
    }

    public Height value(double value) {
        this.value = value;
        return this;
    }
}
