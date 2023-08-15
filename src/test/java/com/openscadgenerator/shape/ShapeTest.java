package com.openscadgenerator.shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.scad.ScadString;

class ShapeTest {

    @Test
    void generate() {
        Shape shape = new Shape() {

            @Override protected ScadString generateShape() {
                return new ScadString("invalid");
            }

            @Override public boolean isInvalid() {
                return true;
            }

            @Override protected Tupel3D getCenter() {
                return null;
            }
        };
        Assertions.assertThrowsExactly(RuntimeException.class, shape::generate);
    }
}