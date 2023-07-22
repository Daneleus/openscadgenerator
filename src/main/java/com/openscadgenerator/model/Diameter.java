package com.openscadgenerator.model;

public class Diameter implements Comparable<Diameter> {

    public static double VALUE_DEFAULT = 100;

    public static Diameter DEFAULT = new Diameter();

    double value = VALUE_DEFAULT;

    @Override
    public int compareTo(Diameter diameter) {
        return Double.compare(this.getValue(), diameter.getValue());
    }

    public double getValue() {
        return value;
    }

    public boolean isNotZero() {
        return this.getValue() != 0;
    }

    public Diameter value(double value) {
        this.value = value;
        return this;
    }
}
