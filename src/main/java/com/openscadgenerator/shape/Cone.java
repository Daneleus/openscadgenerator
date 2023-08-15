package com.openscadgenerator.shape;

import java.util.Locale;

import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.number.DecimalNumber;
import com.openscadgenerator.number.Greater2IntegerNumber;
import com.openscadgenerator.number.IntegerNumber;
import com.openscadgenerator.number.NotNegativeDecimalNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;

public class Cone extends Shape<Cone> {

    private DecimalNumber diameterBottom = new NotNegativeDecimalNumber(100);

    private DecimalNumber diameterTop = new NotNegativeDecimalNumber(0);

    private IntegerNumber fragments = Greater2IntegerNumber.DEFAULT;

    private DecimalNumber height = PositiveDecimalNumber.DEFAULT;

    public Cone diameterBottom(NotNegativeDecimalNumber diameterBottom) {
        this.diameterBottom = diameterBottom;
        return this;
    }

    public Cone diameterTop(NotNegativeDecimalNumber diameterTop) {
        this.diameterTop = diameterTop;
        return this;
    }

    public Cone fragments(Greater2IntegerNumber fragments) {
        this.fragments = fragments;
        return this;
    }

    @Override
    protected ScadString generateShape() {
        return new ScadString(
                String.format(Locale.ENGLISH, "cylinder(h=%.4f,d1=%.4f,d2=%.4f,$fn=%d);", getHeight().value(),
                        getDiameterBottom().value(), getDiameterTop().value(), (long)getFragments().value()));
    }

    @Override
    protected Tupel3D getCenter() {
        return new Tupel3D(0, 0, getHeight().value() / 2.0);
    }

    @Override
    public boolean isInvalid() {
        return getDiameterBottom().value() <= 0 && getDiameterTop().value() <= 0;
    }

    @Override
    public Cone getShape() {
        return this;
    }

    public DecimalNumber getHeight() {
        return height;
    }

    public DecimalNumber getDiameterBottom() {
        return diameterBottom;
    }

    public DecimalNumber getDiameterTop() {
        return diameterTop;
    }

    public IntegerNumber getFragments() {
        return fragments;
    }

    public Cone height(PositiveDecimalNumber height) {
        this.height = height;
        return this;
    }
}
