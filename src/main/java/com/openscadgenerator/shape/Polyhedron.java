package com.openscadgenerator.shape;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.openscadgenerator.geometry.Face;
import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.service.PolyhedronService;
import com.openscadgenerator.service.Tupel3DService;

public class Polyhedron extends Shape<Polyhedron> {

    public static List<Tupel3D> POINTS_DEFAULT = Arrays.asList(
            Tupel3D.ORIGIN,
            new Tupel3D(10, 0, 0),
            new Tupel3D(10, 10, 0),
            new Tupel3D(0, 10, 0),
            new Tupel3D(0, 0, 10),
            new Tupel3D(10, 10, 10)
    );

    public static List<Face> FACES_DEFAULT = PolyhedronService.autoFacing(POINTS_DEFAULT);

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

    @Override protected Tupel3D getCenter() {

        Tupel3D maxDiameterVector = Tupel3DService.maxDiameterVector(points);
        return new Tupel3D((maxDiameterVector.x() - Tupel3DService.OFFSET) / 2.0,
                (maxDiameterVector.y() - Tupel3DService.OFFSET) / 2.0,
                (maxDiameterVector.z() - Tupel3DService.OFFSET) / 2.0);
    }

    @Override
    public boolean isInvalid() {
        return getPoints().isEmpty() || getFaces().isEmpty();
    }

    @Override
    public Polyhedron getShape() {
        return this;
    }

    public List<Tupel3D> getPoints() {
        return points;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public Polyhedron points(List<Tupel3D> points) {
        this.points = points;
        return this;
    }
}
