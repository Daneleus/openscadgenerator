package com.openscadgenerator.model;

@SuppressWarnings("unused")
public class Point2D {

    public static Point2D ORIGIN = new Point2D();

    private double x = 0;

    private double y = 0;

    public Point2D() {
    }

    public boolean isOrigin() {
        return getX() == 0 && getY() == 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override public String toString() {
        return "[" + getX() + "," + getY() + "]";
    }

    public Point2D x(double x) {
        this.x = x;
        return this;
    }

    public Point2D y(double y) {
        this.y = y;
        return this;
    }
}
