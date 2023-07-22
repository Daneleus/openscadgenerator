package com.openscadgenerator.shapes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.openscadgenerator.model.Diameter;
import com.openscadgenerator.model.Fragments;
import com.openscadgenerator.model.Height;
import com.openscadgenerator.model.Point2D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;
import com.openscadgenerator.util.ScadUtil;

@SuppressWarnings("unused")
public class Prism extends Shape<Prism> {

    public static List<Point2D> POINTS_DEFAULT =
            Arrays.asList(new Point2D().x(10), new Point2D().y(10), Point2D.ORIGIN);

    private Diameter diameter = Diameter.DEFAULT;

    private Fragments fragments = Fragments.DEFAULT;

    private Height height = Height.DEFAULT;

    private List<Point2D> points = POINTS_DEFAULT;

    public Prism diameter(Diameter diameter) {
        this.diameter = diameter;
        this.points = Collections.emptyList();
        return this;
    }

    public Prism fragments(Fragments fragments) {
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
                    String.format(Locale.ENGLISH, "linear_extrude(%.4f){circle(d=%.4f, $fn=%d);}",
                            getHeight().getValue(),
                            getDiameter().getValue(), getFragments().getValue()));
        }
        else {
            return new ScadString(
                    String.format(Locale.ENGLISH, "linear_extrude(%.4f){polygon(points=[%s]);}", getHeight().getValue(),
                            getPoints().stream().map(Point2D::toString).collect(Collectors.joining(","))));
        }
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public Height getHeight() {
        return height;
    }

    public Diameter getDiameter() {
        return diameter;
    }

    public Fragments getFragments() {
        return fragments;
    }

    public Prism height(Height height) {
        this.height = height;
        return this;
    }

    public Prism points(List<Point2D> points) {
        this.points = points;
        return this;
    }

}
