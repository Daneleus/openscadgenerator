package com.openscadgenerator.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.openscadgenerator.model.Diameter;
import com.openscadgenerator.model.Height;
import com.openscadgenerator.model.Length;
import com.openscadgenerator.model.Point3D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;
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
    void test_differenceAll() {
        Cuboid cuboid = new Cuboid().xLength(new Length().value(200)).yLength(new Length().value(200))
                .zLength(new Length().value(20));
        Cone cone = new Cone().diameter(new Diameter().value(50));
        Cone cone1 = new Cone().diameter(new Diameter().value(10)).height(new Height().value(100))
                .position(new Point3D().x(80).y(80));
        Cone cone2 = new Cone().diameter(new Diameter().value(10)).height(new Height().value(100))
                .position(new Point3D().x(80).y(-80));
        Cone cone3 = new Cone().diameter(new Diameter().value(10)).height(new Height().value(100))
                .position(new Point3D().x(-80).y(-80));
        Cone cone4 = new Cone().diameter(new Diameter().value(10)).height(new Height().value(100))
                .position(new Point3D().x(-80).y(80));
        ScadString differenceAll =
                ScadUtil.differenceAll(cuboid.generate(), Stream.of(cone, cone1, cone2, cone3, cone4).map(
                        Cone::generate).collect(
                        Collectors.toList()));
        ScadUtil.generateScadFile(
                differenceAll, "src\\test\\samples", "differenceAllTest.scad");
        Assertions.assertEquals(
                "difference(){cube(size=[200.0000,200.0000,20.0000],center=true);union(){cylinder(h=100.0000,d=50.0000,$fn=64);translate([80.0,80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([80.0,-80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([-80.0,-80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([-80.0,80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}}}",
                differenceAll.content());

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

    @Test
    void test_unionAll() {
        Cuboid cuboid = new Cuboid().xLength(new Length().value(200)).yLength(new Length().value(200))
                .zLength(new Length().value(20));
        Cone cone = new Cone().diameter(new Diameter().value(50));
        Cone cone1 = new Cone().diameter(new Diameter().value(10)).height(new Height().value(100))
                .position(new Point3D().x(80).y(80));
        Cone cone2 = new Cone().diameter(new Diameter().value(10)).height(new Height().value(100))
                .position(new Point3D().x(80).y(-80));
        Cone cone3 = new Cone().diameter(new Diameter().value(10)).height(new Height().value(100))
                .position(new Point3D().x(-80).y(-80));
        Cone cone4 = new Cone().diameter(new Diameter().value(10)).height(new Height().value(100))
                .position(new Point3D().x(-80).y(80));
        ScadString unionAll =
                ScadUtil.unionAll(Stream.of(cuboid, cone, cone1, cone2, cone3, cone4).map(
                        Shape::generate).collect(
                        Collectors.toList()));
        ScadUtil.generateScadFile(
                unionAll, "src\\test\\samples", "unionAllTest.scad");
        Assertions.assertEquals(
                "union(){cube(size=[200.0000,200.0000,20.0000],center=true);cylinder(h=100.0000,d=50.0000,$fn=64);translate([80.0,80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([80.0,-80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([-80.0,-80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([-80.0,80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}}",
                unionAll.content());

        ScadString unionAllSelf = ScadUtil.unionAll(Collections.singletonList(unionAll));
        Assertions.assertEquals(
                "union(){cube(size=[200.0000,200.0000,20.0000],center=true);cylinder(h=100.0000,d=50.0000,$fn=64);translate([80.0,80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([80.0,-80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([-80.0,-80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([-80.0,80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}}",
                unionAllSelf.content());

        ScadString unionAllAndOne = ScadUtil.unionAll(Arrays.asList(unionAllSelf,
                new Cone().diameterBottom(new Diameter().value(80)).diameterTop(new Diameter().value(0)).generate()));
        ScadUtil.generateScadFile(
                unionAllAndOne, "src\\test\\samples", "unionAllAndOneTest.scad");
        Assertions.assertEquals(
                "union(){union(){cube(size=[200.0000,200.0000,20.0000],center=true);cylinder(h=100.0000,d=50.0000,$fn=64);translate([80.0,80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([80.0,-80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([-80.0,-80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([-80.0,80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}}cylinder(h=100.0000,d1=80.0000,d2=0.0000,$fn=64);}",
                unionAllAndOne.content());
    }
}