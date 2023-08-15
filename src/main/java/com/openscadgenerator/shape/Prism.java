package com.openscadgenerator.shape;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.openscadgenerator.geometry.Tupel2D;
import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.number.DecimalNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;

public class Prism extends Shape<Prism> {

    public static List<Tupel2D> POINTS_DEFAULT =
            Arrays.asList(new Tupel2D(100, 0), new Tupel2D(0, 100), new Tupel2D(-10, 0), new Tupel2D(0, -100));

    private DecimalNumber height = PositiveDecimalNumber.DEFAULT;

    private List<Tupel2D> points = POINTS_DEFAULT;

    @Override
    protected ScadString generateShape() {
        return new ScadString(
                String.format(Locale.ENGLISH, "linear_extrude(%.4f){polygon(points=[%s]);}", getHeight().value(),
                        getPoints().stream().map(Tupel2D::toString).collect(Collectors.joining(","))));
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
    public Prism getShape() {
        return this;
    }

    public DecimalNumber getHeight() {
        return height;
    }

    public List<Tupel2D> getPoints() {
        return points;
    }

    public Prism height(DecimalNumber height) {
        this.height = height;
        return this;
    }

    public Prism points(List<Tupel2D> points) {
        this.points = points;
        return this;
    }

}
