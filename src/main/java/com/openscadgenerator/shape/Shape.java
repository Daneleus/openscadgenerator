package com.openscadgenerator.shape;

import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.scad.ScadString;

public abstract class Shape<T extends Shape<T>> {

    public final ScadString generate() {
        ScadString scadString = this.generateShape();
        if (isInvalid()) {
            throw new RuntimeException(
                    "invalid shape: " + scadString.content());
        }
        return scadString;
    }

    protected abstract ScadString generateShape();

    public abstract boolean isInvalid();

    protected abstract Tupel3D getCenter();

    public abstract T getShape();

}
