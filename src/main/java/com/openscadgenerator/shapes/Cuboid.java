package com.openscadgenerator.shapes;

import java.util.Locale;

import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;
import com.openscadgenerator.util.ScadUtil;

@SuppressWarnings("unused")
public class Cuboid extends Shape<Cuboid> {
    public static double X_DEFAULT = 100;

    public static double Y_DEFAULT = 100;

    public static double Z_DEFAULT = 100;

    private double x = X_DEFAULT;

    private double y = Y_DEFAULT;

    private double z = Z_DEFAULT;

    public Cuboid cube(double a) {
        this.x = a;
        this.y = a;
        this.z = a;
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

    private ScadString generateCuboid() {
        return new ScadString(
                String.format(Locale.ENGLISH, "cube(size=[%.4f,%.4f,%.4f],center=true);", getX(), getY(), getZ()));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Cuboid x(double x) {
        this.x = x;
        return this;
    }

    public Cuboid y(double y) {
        this.y = y;
        return this;
    }

    public Cuboid z(double z) {
        this.z = z;
        return this;
    }
}
