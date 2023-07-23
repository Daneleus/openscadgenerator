package com.openscadgenerator.shapes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.model.Face;
import com.openscadgenerator.model.Point3D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;
import com.openscadgenerator.util.ScadUtil;

class PolyhedronTest {

    static List<Shape<Polyhedron>> shapelist = new ArrayList<>();

    @AfterAll
    static void generateTestFile() {
        double[] xPos = { 0 };
        ScadUtil.generateScadFile(
                shapelist.stream().map(shape -> shape.position(new Point3D().x(xPos[0] += 100)))
                        .collect(Collectors.toList()), "src\\test\\samples", "polyhedronTest.scad");
    }

    @BeforeAll
    static void initShapes() {
        shapelist.add(new Polyhedron()
                .points(Arrays.asList(
                                Point3D.ORIGIN,
                                new Point3D().x(10),
                                new Point3D().x(10).y(10),
                                new Point3D().y(10),
                                new Point3D().z(10),
                                new Point3D().x(10).y(10).z(10)
                        )
                )
                .faces(Arrays.asList(
                                new Face(Arrays.asList(0, 1, 2, 3)),
                                new Face(Arrays.asList(0, 1, 4)),
                                new Face(Arrays.asList(0, 3, 4)),
                                new Face(Arrays.asList(1, 4, 5)),
                                new Face(Arrays.asList(1, 2, 5)),
                                new Face(Arrays.asList(2, 3, 5)),
                                new Face(Arrays.asList(3, 4, 5))
                        )
                )
        );
    }

    @Test
    void test_poyhedron6Points() {
        ScadString scadString = shapelist.get(0).generate();
        Assertions.assertEquals(
                "polyhedron(points=[[0.0,0.0,0.0],[10.0,0.0,0.0],[10.0,10.0,0.0],[0.0,10.0,0.0],[0.0,0.0,10.0],[10.0,10.0,10.0]],faces=[[0,1,2,3],[0,1,4],[0,3,4],[1,4,5],[1,2,5],[2,3,5],[3,4,5]]);",
                scadString.content());
    }

}