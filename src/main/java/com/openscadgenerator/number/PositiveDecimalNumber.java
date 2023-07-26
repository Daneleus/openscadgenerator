package com.openscadgenerator.number;

public record PositiveDecimalNumber(double value) implements DecimalNumber {

    private static final double VALUE_DEFAULT = 100;

    public static PositiveDecimalNumber DEFAULT = new PositiveDecimalNumber(VALUE_DEFAULT);

    public PositiveDecimalNumber {
        if (value <= 0) {
            throw new IllegalArgumentException("value must be positive!");
        }
    }

}
