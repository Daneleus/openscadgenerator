package com.openscadgenerator.geometry;

public record Tupel2D(double x, double y) {

    public static Tupel2D ORIGIN = new Tupel2D(0, 0);

    public Tupel3D convertTo3D() {
        return new Tupel3D(x, y, 0);
    }

    public boolean isOrigin() {
        return this.equals(ORIGIN);
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
