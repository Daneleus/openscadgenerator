package com.openscadgenerator.model;

public class Length {

    public static double VALUE_DEFAULT = 100;

    public static Length DEFAULT = new Length();

    double value = VALUE_DEFAULT;

    public double getValue() {
        return value;
    }

    public Length value(double value) {
        this.value = value;
        return this;
    }
}
