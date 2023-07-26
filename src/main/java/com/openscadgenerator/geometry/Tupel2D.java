package com.openscadgenerator.geometry;

public record Tupel2D(double x, double y) {

    public static Tupel2D ORIGIN = new Tupel2D(0, 0);

    private static final double X_DEFAULT = 0;

    private static final double Y_DEFAULT = 0;

    public static Tupel2D DEFAULT = new Tupel2D(X_DEFAULT, Y_DEFAULT);

    public boolean isOrigin() {
        return this.equals(ORIGIN);
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
