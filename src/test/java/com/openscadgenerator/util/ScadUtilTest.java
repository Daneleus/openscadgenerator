package com.openscadgenerator.util;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.shape.Cube;
import com.openscadgenerator.shape.Cuboid;
import com.openscadgenerator.shape.Cylinder;

class ScadUtilTest {

    @AfterAll
    static void cleanupFiles() throws InterruptedException, IOException {
        FileUtil.deleteDirectory(FileUtilTest.TEST_DIR);
    }

    @Test
    void difference() {
        Cuboid cuboid = new Cuboid().xLength(new PositiveDecimalNumber(200)).yLength(new PositiveDecimalNumber(200))
                .zLength(new PositiveDecimalNumber(20));
        Cylinder cylinder = new Cylinder().diameter(new PositiveDecimalNumber(200));
        ScadString differenceAll = ScadUtil.difference(cuboid.generate(), cylinder.generate());
        ScadUtil.generateScadFile(differenceAll, "src\\test\\samples", "differenceTest.scad");
        Assertions.assertEquals(
                "difference(){cube(size=[200.0000,200.0000,20.0000],center=true);cylinder(h=100.0000,d=200.0000,$fn=100);}",
                differenceAll.content());
    }

    @Test
    void generateScadFile() {
        ScadUtil.generateScadFile(new ScadString(""), FileUtilTest.TEST_DIR, FileUtilTest.TEST_FILENAME);
        Assertions.assertTrue(new File(FileUtilTest.TEST_DIR + "\\" + FileUtilTest.TEST_FILENAME).isFile());
    }

    @Test
    void intersection() {
        Cube cube = new Cube();
        Cylinder cylinder = new Cylinder();
        ScadString differenceAll =
                ScadUtil.intersection(cube.generate(), cylinder.generate());
        ScadUtil.generateScadFile(differenceAll, "src\\test\\samples", "intersectionTest.scad");
        Assertions.assertEquals(
                "intersection(){cube(size=100.0000,center=true);cylinder(h=100.0000,d=100.0000,$fn=100);}",
                differenceAll.content());
    }

    @Test
    void moveToPosition() {
        ScadString move = ScadUtil.moveToPosition(new Tupel3D(1, 1, 1), new ScadString("moved"));
        Assertions.assertEquals("translate([1.0,1.0,1.0]){moved}", move.content());
    }

    @Test
    void rotate_vector() {
        ScadString rotate = ScadUtil.rotate(new Cube().generate(), new Tupel3D(0, 0, 1), 45);
        ScadUtil.generateScadFile(rotate, FileUtilTest.TEST_DIR, FileUtilTest.TEST_FILENAME);
        Assertions.assertEquals("rotate(a=45.0, v=[0.0,0.0,1.0]){cube(size=100.0000,center=true);}", rotate.content());
    }

    @Test
    void union() {
        Cuboid cuboid = new Cuboid().xLength(new PositiveDecimalNumber(200)).yLength(new PositiveDecimalNumber(200))
                .zLength(new PositiveDecimalNumber(20));
        Cylinder cylinder = new Cylinder();
        ScadString unionAll = ScadUtil.union(cuboid.generate(), cylinder.generate());
        ScadUtil.generateScadFile(unionAll, "src\\test\\samples", "unionTest.scad");
        Assertions.assertEquals(
                "union(){cube(size=[200.0000,200.0000,20.0000],center=true);cylinder(h=100.0000,d=100.0000,$fn=100);}",
                unionAll.content());
    }
}