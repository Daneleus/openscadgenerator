package com.openscadgenerator.shape;

import java.util.Locale;

import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.number.DecimalNumber;
import com.openscadgenerator.number.Greater2IntegerNumber;
import com.openscadgenerator.number.IntegerNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;

public class Cylinder extends Shape<Cylinder> {

    private DecimalNumber diameter = new PositiveDecimalNumber(100);

    private IntegerNumber fragments = Greater2IntegerNumber.DEFAULT;

    private DecimalNumber height = PositiveDecimalNumber.DEFAULT;

    public Cylinder diameter(PositiveDecimalNumber diameter) {
        this.diameter = diameter;
        return this;
    }

    public Cylinder fragments(Greater2IntegerNumber fragments) {
        this.fragments = fragments;
        return this;
    }

    @Override
    protected ScadString generateShape() {
        return new ScadString(String.format(Locale.ENGLISH, "cylinder(h=%.4f,d=%.4f,$fn=%d);", getHeight().value(),
                getDiameter().value(), (long)getFragments().value()));
    }

    @Override
    protected Tupel3D getCenter() {
        return new Tupel3D(0, 0, getHeight().value() / 2.0);
    }

    @Override
    public boolean isInvalid() {
        return false;
    }

    @Override
    public Cylinder getShape() {
        return this;
    }

    public DecimalNumber getHeight() {
        return height;
    }

    public DecimalNumber getDiameter() {
        return diameter;
    }

    public IntegerNumber getFragments() {
        return fragments;
    }

    public Cylinder height(PositiveDecimalNumber height) {
        this.height = height;
        return this;
    }
}
