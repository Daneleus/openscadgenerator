package com.openscadgenerator.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.model.Point3D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.shapes.Cone;
import com.openscadgenerator.shapes.Cuboid;
import com.openscadgenerator.shapes.Polyhedron;
import com.openscadgenerator.shapes.Prism;

class ScadUtilTest {

    @AfterAll
    static void cleanupFiles() throws InterruptedException, IOException {
        FileUtil.deleteDirectory(FileUtilTest.TEST_DIR);
    }

    @Test
    void difference() {
        ScadString difference = ScadUtil.difference(new ScadString("minuent"), new ScadString("subtrahent"));
        Assertions.assertEquals("difference(){minuentsubtrahent}", difference.content());
    }

    @Test
    void generateScad() {
        ScadUtil.generateScad(Arrays.asList(new Cone(), new Cuboid(), new Prism(), new Polyhedron()),
                FileUtilTest.TEST_DIR,
                FileUtilTest.TEST_FILENAME);
        Assertions.assertTrue(new File(FileUtilTest.TEST_DIR + "\\" + FileUtilTest.TEST_FILENAME).isFile());
    }

    @Test
    void moveToPosition() {
        ScadString move = ScadUtil.moveToPosition(Point3D.ORIGIN, new ScadString("moved"));
        Assertions.assertEquals("translate([0.0,0.0,0.0]){moved}", move.content());
    }
}