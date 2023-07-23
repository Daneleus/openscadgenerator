package com.openscadgenerator.shapes;

import java.util.Locale;

import com.openscadgenerator.model.Length;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;
import com.openscadgenerator.util.ScadUtil;

@SuppressWarnings("unused")
public class Cuboid extends Shape<Cuboid> {
    private Length xLength = Length.DEFAULT;

    private Length yLength = Length.DEFAULT;

    private Length zLength = Length.DEFAULT;

    public Cuboid cube(Length length) {
        this.xLength = length;
        this.yLength = length;
        this.zLength = length;
        return this;
    }

    @Override
    public ScadString generate() {
        if (getPosition().isOrigin()) {
            return generateCuboid();
        }
        else {
            return ScadUtil.moveToPosition(getPosition(), generateCuboid());
        }
    }

    @Override public boolean isInvalid() {
        return xLength.getValue() <= 0 || yLength.getValue() <= 0 || zLength.getValue() <= 0;
    }

    private ScadString generateCuboid() {
        return new ScadString(
                String.format(Locale.ENGLISH, "cube(size=[%.4f,%.4f,%.4f],center=true);", getXLength().getValue(),
                        getYLength().getValue(), getZLength().getValue()));
    }

    public Length getXLength() {
        return xLength;
    }

    public Length getYLength() {
        return yLength;
    }

    public Length getZLength() {
        return zLength;
    }

    public Cuboid xLength(Length length) {
        this.xLength = length;
        return this;
    }

    public Cuboid yLength(Length length) {
        this.yLength = length;
        return this;
    }

    public Cuboid zLength(Length length) {
        this.zLength = length;
        return this;
    }
}
