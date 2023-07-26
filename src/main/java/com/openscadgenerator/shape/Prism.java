package com.openscadgenerator.shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.openscadgenerator.geometry.Tupel2D;
import com.openscadgenerator.number.DecimalNumber;
import com.openscadgenerator.number.Greater2IntegerNumber;
import com.openscadgenerator.number.IntegerNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;

public class Prism extends Shape<Prism> {

    public static List<Tupel2D> POINTS_DEFAULT = new ArrayList<>();

    private DecimalNumber diameter = PositiveDecimalNumber.DEFAULT;

    private IntegerNumber fragments = Greater2IntegerNumber.DEFAULT;

    private DecimalNumber height = PositiveDecimalNumber.DEFAULT;

    private List<Tupel2D> points = POINTS_DEFAULT;

    public Prism diameter(PositiveDecimalNumber diameter) {
        this.diameter = diameter;
        this.points = Collections.emptyList();
        return this;
    }

    public Prism fragments(Greater2IntegerNumber fragments) {
        this.fragments = fragments;
        return this;
    }

    @Override
    protected ScadString generateShape() {
        if (getPoints().size() < 3) {
            return new ScadString(
                    String.format(Locale.ENGLISH, "linear_extrude(%.4f){circle(d=%.4f, $fn=%d);}",
                            getHeight().value(),
                            getDiameter().value(), getFragments().value()));
        }
        else {
            return new ScadString(
                    String.format(Locale.ENGLISH, "linear_extrude(%.4f){polygon(points=[%s]);}", getHeight().value(),
                            getPoints().stream().map(Tupel2D::toString).collect(Collectors.joining(","))));
        }
    }

    public List<Tupel2D> getPoints() {
        return points;
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

    @Override
    public boolean isInvalid() {
        return false;
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
