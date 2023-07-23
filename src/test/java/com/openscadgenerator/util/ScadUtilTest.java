package com.openscadgenerator.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.openscadgenerator.model.Length;
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
    void test_difference() {
        ScadString difference = ScadUtil.difference(new ScadString("minuent"), new ScadString("subtrahent"));
        Assertions.assertEquals("difference(){minuentsubtrahent}", difference.content());
    }

    @Test
    void test_generateScadFileScadString() {
        ScadUtil.generateScadFile(new Cone().generate(),
                FileUtilTest.TEST_DIR,
                FileUtilTest.TEST_FILENAME);
        Assertions.assertTrue(new File(FileUtilTest.TEST_DIR + "\\" + FileUtilTest.TEST_FILENAME).isFile());
    }

    @Test
    void test_generateScadFileShapeList() {
        ScadUtil.generateScadFile(Arrays.asList(new Cone(), new Cuboid(), new Prism(), new Polyhedron()),
                FileUtilTest.TEST_DIR,
                FileUtilTest.TEST_FILENAME);
        Assertions.assertTrue(new File(FileUtilTest.TEST_DIR + "\\" + FileUtilTest.TEST_FILENAME).isFile());
    }

    @Test
    void test_generateScadFileSingleShape() {
        ScadUtil.generateScadFile(new Cone(),
                FileUtilTest.TEST_DIR,
                FileUtilTest.TEST_FILENAME);
        Assertions.assertTrue(new File(FileUtilTest.TEST_DIR + "\\" + FileUtilTest.TEST_FILENAME).isFile());
    }

    @Test
    void test_generateScad_invalid() {
        Executable ex =
                () -> ScadUtil.generateScadFile(Collections.singletonList(new Cuboid().xLength(new Length().value(0))),
                        FileUtilTest.TEST_DIR,
                        FileUtilTest.TEST_FILENAME);
        Assertions.assertThrows(RuntimeException.class, ex);
    }

    @Test
    void test_moveToPosition() {
        ScadString move = ScadUtil.moveToPosition(Point3D.ORIGIN, new ScadString("moved"));
        Assertions.assertEquals("translate([0.0,0.0,0.0]){moved}", move.content());
    }
}