package com.openscadgenerator.shape;

import java.util.Locale;

import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.number.DecimalNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;

public class Cube extends Shape<Cube> {
    private DecimalNumber length = PositiveDecimalNumber.DEFAULT;

    @Override
    protected ScadString generateShape() {
        return new ScadString(String.format(Locale.ENGLISH, "cube(size=%.4f,center=true);", getLength().value()));
    }

    @Override
    protected Tupel3D getCenter() {
        return Tupel3D.ORIGIN;
    }

    @Override
    public boolean isInvalid() {
        return false;
    }

    @Override
    public Cube getShape() {
        return this;
    }

    public DecimalNumber getLength() {
        return length;
    }

    public Cube length(PositiveDecimalNumber length) {
        this.length = length;
        return this;
    }

}
