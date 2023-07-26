package com.openscadgenerator.geometry;

public record Tupel3D(double x, double y, double z) {

    public static Tupel3D ORIGIN = new Tupel3D(0, 0, 0);

    private static final double X_DEFAULT = 0;

    private static final double Y_DEFAULT = 0;

    private static final double Z_DEFAULT = 0;

    public static Tupel3D DEFAULT = new Tupel3D(X_DEFAULT, Y_DEFAULT, Z_DEFAULT);

    public boolean isOrigin() {
        return this.equals(ORIGIN);
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "," + z + "]";
    }
}
