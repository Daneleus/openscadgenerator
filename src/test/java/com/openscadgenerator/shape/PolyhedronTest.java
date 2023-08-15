package com.openscadgenerator.shape;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Face;
import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.service.FileService;

class PolyhedronTest {

    @Test
    void test_generate_invalid() {
        Assertions.assertThrows(RuntimeException.class, () -> new Polyhedron().points(new ArrayList<>()).generate());
    }

    @Test
    void test_poyhedron6Points() {
        ScadString scadString = new Polyhedron()
                .points(Arrays.asList(
                                Tupel3D.ORIGIN,
                                new Tupel3D(10, 0, 0),
                                new Tupel3D(10, 10, 0),
                                new Tupel3D(0, 10, 0),
                                new Tupel3D(0, 0, 10),
                                new Tupel3D(10, 10, 10)
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
                ).generate();
        Assertions.assertEquals(
                "polyhedron(points=[[0.0,0.0,0.0],[10.0,0.0,0.0],[10.0,10.0,0.0],[0.0,10.0,0.0],[0.0,0.0,10.0],[10.0,10.0,10.0]],faces=[[0,1,2,3],[0,1,4],[0,3,4],[1,4,5],[1,2,5],[2,3,5],[3,4,5]]);",
                scadString.content());
    }

    @Test
    void defaultValues() {
        ScadString scadString = new Polyhedron().generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "polyhedronDefault");
        Assertions.assertEquals(
                "polyhedron(points=[[0.0,0.0,0.0],[10.0,0.0,0.0],[10.0,10.0,0.0],[0.0,10.0,0.0],[0.0,0.0,10.0],[10.0,10.0,10.0]],faces=[[1,0,2],[1,0,3],[4,0,1],[2,0,3],[3,0,4],[2,1,3],[5,1,2],[4,1,5],[5,2,3],[5,3,4]]);",
                scadString.content());
    }

}