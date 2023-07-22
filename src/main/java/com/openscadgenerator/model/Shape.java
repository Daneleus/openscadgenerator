package com.openscadgenerator.model;

@SuppressWarnings("unused")
public abstract class Shape<T extends Shape<T>> {

    protected Point3D position = Point3D.ORIGIN;

    public abstract ScadString generate();

    @SuppressWarnings("unchecked")
    public T get() {
        return (T)this;
    }

    public Point3D getPosition() {
        return position;
    }

    public abstract boolean isInvalid();

    @SuppressWarnings("unchecked")
    public T position(Point3D position) {
        this.position = position;
        return (T)this;
    }
}
