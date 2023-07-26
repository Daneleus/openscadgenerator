package com.openscadgenerator.shape;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.openscadgenerator.geometry.Face;
import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.scad.ScadString;

public class Polyhedron extends Shape<Polyhedron> {

    public static List<Face> FACES_DEFAULT =
            Arrays.asList(new Face(Arrays.asList(0, 1, 2)), new Face(Arrays.asList(0, 1, 3)),
                    new Face(Arrays.asList(0, 2, 3)), new Face(Arrays.asList(1, 2, 3)));

    public static List<Tupel3D> POINTS_DEFAULT =
            Arrays.asList(Tupel3D.ORIGIN, new Tupel3D(10, 0, 0), new Tupel3D(0, 10, 0), new Tupel3D(0, 0, 10));

    private List<Face> faces = FACES_DEFAULT;

    private List<Tupel3D> points = POINTS_DEFAULT;

    public Polyhedron faces(List<Face> faces) {
        this.faces = faces;
        return this;
    }

    @Override
    protected ScadString generateShape() {
        return new ScadString(String.format(Locale.ENGLISH, "polyhedron(points=[%s],faces=[%s]);",
                getPoints().stream().map(Tupel3D::toString).collect(Collectors.joining(",")),
                getFaces().stream().map(Face::toString).collect(Collectors.joining(","))));
    }

    public List<Tupel3D> getPoints() {
        return points;
    }

    public List<Face> getFaces() {
        return faces;
    }

    @Override
    public boolean isInvalid() {
        return getPoints().isEmpty() || getFaces().isEmpty();
    }

    public Polyhedron points(List<Tupel3D> points) {
        this.points = points;
        return this;
    }
}
