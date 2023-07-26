package com.openscadgenerator.number;

public record Greater2IntegerNumber(int value) implements IntegerNumber {

    private static final int VALUE_DEFAULT = 100;

    public static Greater2IntegerNumber DEFAULT = new Greater2IntegerNumber(VALUE_DEFAULT);

    public Greater2IntegerNumber {
        if (value <= 2) {
            throw new IllegalArgumentException("value must be greater than 2!");
        }
    }

}
