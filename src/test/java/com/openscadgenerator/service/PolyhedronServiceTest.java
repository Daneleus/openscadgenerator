package com.openscadgenerator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Face;
import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.shape.Polyhedron;

class PolyhedronServiceTest {

    @Test
    void autoFacesRandom() {
        List<Tupel3D> points = new ArrayList<>();
        IntStream.range(0, 50).forEach(i -> points.add(
                new Tupel3D((Math.random() * Math.random() * 100) - Math.random() * 50,
                        (Math.random() * Math.random() * 100) - Math.random() * 50,
                        (Math.random() * Math.random() * 100) - Math.random() * 50)));
        List<Face> autoFaces = PolyhedronService.autoFacing(points);
        Polyhedron autoFacedPolyhedron = new Polyhedron().points(points).faces(autoFaces);
        ScadString scadString = autoFacedPolyhedron.generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "facesAutoRandom");
    }

    @Test
    void autoFacing() {
        List<Tupel3D> points = Arrays.asList(
                Tupel3D.ORIGIN,
                new Tupel3D(10, 0, 0),
                new Tupel3D(10, 10, 0),
                new Tupel3D(0, 10, 0),
                new Tupel3D(0, 0, 10),
                new Tupel3D(10, 10, 10)
        );
        List<Face> facesExpected = Arrays.asList(
                new Face(Arrays.asList(1, 0, 2)),
                new Face(Arrays.asList(1, 0, 3)),
                new Face(Arrays.asList(2, 1, 3)),
                new Face(Arrays.asList(4, 0, 1)),
                new Face(Arrays.asList(2, 0, 3)),
                new Face(Arrays.asList(5, 1, 2)),
                new Face(Arrays.asList(5, 2, 3)),
                new Face(Arrays.asList(5, 3, 4)),
                new Face(Arrays.asList(3, 0, 4)),
                new Face(Arrays.asList(4, 1, 5))
        );
        List<Face> autoFaces = PolyhedronService.autoFacing(points);
        ScadString scadString = new Polyhedron().points(points).faces(autoFaces).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "facesAuto");
        Assertions.assertEquals(new HashSet<>(facesExpected), new HashSet<>(autoFaces));
        ScadString scadStringSame = PolyhedronService.autoFacing(new Polyhedron().points(points)).generate();
        Assertions.assertEquals(scadString, scadStringSame);
    }

}