package com.openscadgenerator.number;

public record NotNegativeDecimalNumber(double value) implements DecimalNumber {

    private static final double VALUE_DEFAULT = 0;

    public static NotNegativeDecimalNumber DEFAULT = new NotNegativeDecimalNumber(VALUE_DEFAULT);

    public NotNegativeDecimalNumber {
        if (value < 0) {
            throw new IllegalArgumentException("value must be not negative!");
        }
    }

}
