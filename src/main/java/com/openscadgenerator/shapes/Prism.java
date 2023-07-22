package com.openscadgenerator.shapes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.openscadgenerator.model.Point2D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;
import com.openscadgenerator.util.ScadUtil;

//TODO use Objects instead primitive types
@SuppressWarnings("unused")
public class Prism extends Shape<Prism> {

    public static double DIAMETER_DEFAULT = 25;

    public static double FRAGMENTS_DEFAULT = 64;

    public static double HEIGHT_DEFAULT = 100;

    public static List<Point2D> POINTS_DEFAULT =
            Arrays.asList(new Point2D().x(10), new Point2D().y(10), Point2D.ORIGIN);

    private double diameter = DIAMETER_DEFAULT;

    private double fragments = FRAGMENTS_DEFAULT;

    private double height = HEIGHT_DEFAULT;

    private List<Point2D> points = POINTS_DEFAULT;

    public Prism diameter(double diameter) {
        this.diameter = diameter;
        this.points = Collections.emptyList();
        return this;
    }

    public Prism fragments(double fragments) {
        this.fragments = fragments;
        return this;
    }

    @Override
    public ScadString generate() {
        if (getPosition().isOrigin()) {
            return generatePrism();
        }
        else {
            return ScadUtil.moveToPosition(getPosition(), generatePrism());
        }
    }

    private ScadString generatePrism() {
        if (getPoints().isEmpty()) {
            return new ScadString(
                    String.format(Locale.ENGLISH, "linear_extrude(%.4f){circle(d=%.4f, $fn=%.4f);}", getHeight(),
                            getDiameter(), getFragments()));
        }
        else {
            return new ScadString(
                    String.format(Locale.ENGLISH, "linear_extrude(%.4f){polygon(points=[%s]);}", getHeight(),
                            getPoints().stream().map(Point2D::toString).collect(Collectors.joining(","))));
        }
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public double getHeight() {
        return height;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getFragments() {
        return fragments;
    }

    public Prism height(double height) {
        this.height = height;
        return this;
    }

    public Prism points(List<Point2D> points) {
        this.points = points;
        return this;
    }

}
