package com.openscadgenerator.shape;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.openscadgenerator.model.Face;
import com.openscadgenerator.model.Point3D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;
import com.openscadgenerator.util.ScadUtil;

public class Polyhedron extends Shape<Polyhedron> {

    public static List<Face> FACES_DEFAULT =
            Arrays.asList(new Face(Arrays.asList(0, 1, 2)), new Face(Arrays.asList(0, 1, 3)),
                    new Face(Arrays.asList(0, 2, 3)), new Face(Arrays.asList(1, 2, 3)));

    public static List<Point3D> POINTS_DEFAULT =
            Arrays.asList(Point3D.ORIGIN, new Point3D().x(10), new Point3D().y(10), new Point3D().z(10));

    private List<Face> faces = FACES_DEFAULT;

    private List<Point3D> points = POINTS_DEFAULT;

    public Polyhedron faces(List<Face> faces) {
        this.faces = faces;
        return this;
    }

    @Override
    public ScadString generate() {
        ScadString scadString = generatePolyhedron();
        if (isInvalid()) {
            throw new RuntimeException(
                    "invalid shape: " + scadString.content());
        }
        if (getPosition().isOrigin()) {
            return scadString;
        }
        else {
            return ScadUtil.moveToPosition(getPosition(), scadString);
        }
    }

    private ScadString generatePolyhedron() {
        return new ScadString(String.format(Locale.ENGLISH, "polyhedron(points=[%s],faces=[%s]);",
                getPoints().stream().map(Point3D::toString).collect(Collectors.joining(",")),
                getFaces().stream().map(Face::toString).collect(Collectors.joining(","))));
    }

    public List<Point3D> getPoints() {
        return points;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public Polyhedron points(List<Point3D> points) {
        this.points = points;
        return this;
    }

    @Override public boolean isInvalid() {
        return getPoints().isEmpty() || getFaces().isEmpty();
    }
}
