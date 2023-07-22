package com.openscadgenerator.model;

@SuppressWarnings("unused")
public class Point3D {

    public static Point3D ORIGIN = new Point3D();

    private double x = 0;

    private double y = 0;

    private double z = 0;

    public Point3D() {
    }

    public boolean isOrigin() {
        return getX() == 0 && getY() == 0 && getZ() == 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override public String toString() {
        return "[" + getX() + "," + getY() + "," + getZ() + "]";
    }

    public Point3D x(double x) {
        this.x = x;
        return this;
    }

    public Point3D y(double y) {
        this.y = y;
        return this;
    }

    public Point3D z(double z) {
        this.z = z;
        return this;
    }
}
