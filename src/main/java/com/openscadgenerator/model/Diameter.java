package com.openscadgenerator.model;

public class Diameter {

    public static double VALUE_DEFAULT = 100;

    public static Diameter DEFAULT = new Diameter();

    double value = VALUE_DEFAULT;

    public double getValue() {
        return value;
    }

    public Diameter value(double value) {
        this.value = value;
        return this;
    }
}
