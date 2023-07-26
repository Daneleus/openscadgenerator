package com.openscadgenerator.shape;

import java.util.Locale;

import com.openscadgenerator.number.DecimalNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;

public class Cube extends Shape<Cube> {
    private DecimalNumber length = PositiveDecimalNumber.DEFAULT;

    @Override
    protected ScadString generateShape() {
        return new ScadString(String.format(Locale.ENGLISH, "cube(size=%.4f,center=true);", getLength().value()));
    }

    public DecimalNumber getLength() {
        return length;
    }

    @Override
    public boolean isInvalid() {
        return false;
    }

    public Cube length(PositiveDecimalNumber length) {
        this.length = length;
        return this;
    }

}
