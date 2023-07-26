package com.openscadgenerator.shape;

import java.util.Locale;

import com.openscadgenerator.number.DecimalNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;

public class Cuboid extends Shape<Cuboid> {
    private DecimalNumber xLength = PositiveDecimalNumber.DEFAULT;

    private DecimalNumber yLength = new PositiveDecimalNumber(50);

    private DecimalNumber zLength = new PositiveDecimalNumber(200);

    @Override
    protected ScadString generateShape() {
        return new ScadString(
                String.format(Locale.ENGLISH, "cube(size=[%.4f,%.4f,%.4f],center=true);", getXLength().value(),
                        getYLength().value(), getZLength().value()));
    }

    public DecimalNumber getXLength() {
        return xLength;
    }

    public DecimalNumber getYLength() {
        return yLength;
    }

    public DecimalNumber getZLength() {
        return zLength;
    }

    @Override
    public boolean isInvalid() {
        return false;
    }

    public Cuboid xLength(PositiveDecimalNumber length) {
        this.xLength = length;
        return this;
    }

    public Cuboid yLength(PositiveDecimalNumber length) {
        this.yLength = length;
        return this;
    }

    public Cuboid zLength(PositiveDecimalNumber length) {
        this.zLength = length;
        return this;
    }
}
