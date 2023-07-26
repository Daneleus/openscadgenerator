package com.openscadgenerator.shape;

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

}
