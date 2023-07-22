package com.openscadgenerator.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.model.Diameter;
import com.openscadgenerator.model.Fragments;
import com.openscadgenerator.model.Height;
import com.openscadgenerator.model.Point3D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;
import com.openscadgenerator.util.ScadUtil;

class ConeTest {

    static List<Shape<Cone>> shapelist = new ArrayList<>();

    @AfterAll
    static void generateTestFile() {
        double[] xPos = { 0 };
        ScadUtil.generateScad(shapelist.stream().map(shape -> shape.position(new Point3D().x(xPos[0] += 100)))
                .collect(Collectors.toList()), "src\\test\\samples", "coneTest.scad");
    }

    @BeforeAll
    static void initShapes() {
        shapelist.add(new Cone());
        shapelist.add(new Cone().diameter(new Diameter().value(50)).position(Point3D.ORIGIN));
        shapelist.add(new Cone().diameter(new Diameter().value(50)).innerDiameter(new Diameter().value(25)));
        shapelist.add(new Cone().diameter(new Diameter().value(50)).innerDiameterBottom(new Diameter().value(25))
                .innerDiameterTop(new Diameter().value(35)));
        shapelist.add(new Cone().diameterBottom(new Diameter().value(80)).diameterTop(new Diameter().value(50))
                .innerDiameter(new Diameter().value(25)));
        shapelist.add(new Cone().diameterBottom(new Diameter().value(50)).diameterTop(new Diameter().value(35)));
        shapelist.add(new Cone().diameterBottom(new Diameter().value(50)).diameterTop(new Diameter().value(35))
                .innerDiameterBottom(new Diameter().value(40)).innerDiameterTop(new Diameter().value(25)));
        shapelist.add(new Cone().height(new Height().value(80)));
        shapelist.add(
                new Cone().diameterBottom(new Diameter().value(50)).diameterTop(new Diameter().value(35))
                        .innerDiameterBottom(new Diameter().value(25)).innerDiameterTop(new Diameter().value(10))
                        .fragments(new Fragments().value(6))
                        .innerFragments(new Fragments().value(3)));
        shapelist.add(new Cone().position(new Point3D().x(1).y(2).z(3)));
    }

    @Test
    void test_cone() {
        Shape<Cone> cone = shapelist.get(5);
        ScadString scadString = cone.generate();
        Assertions.assertEquals("cylinder(h=100.0000,d1=50.0000,d2=35.0000,$fn=64);", scadString.content());
        Assertions.assertFalse(cone.get().isConeTube());
        Assertions.assertTrue(cone.get().isCone());
        Assertions.assertFalse(cone.get().isCylinderTube());
        Assertions.assertFalse(cone.get().isCylinder());
    }

    @Test
    void test_coneCylinderTube() {
        Shape<Cone> cone = shapelist.get(4);
        ScadString scadString = cone.generate();
        Assertions.assertEquals(
                "difference(){cylinder(h=100.0000,d1=80.0000,d2=50.0000,$fn=64);cylinder(h=100.0000,d1=25.0000,d2=25.0000,$fn=64);}",
                scadString.content());
        Assertions.assertTrue(cone.get().isConeTube());
        Assertions.assertTrue(cone.get().isCone());
        Assertions.assertFalse(cone.get().isCylinderTube());
        Assertions.assertFalse(cone.get().isCylinder());
    }

    @Test
    void test_coneTube() {
        Shape<Cone> cone = shapelist.get(6);
        ScadString scadString = cone.generate();
        Assertions.assertEquals(
                "difference(){cylinder(h=100.0000,d1=50.0000,d2=35.0000,$fn=64);cylinder(h=100.0000,d1=40.0000,d2=25.0000,$fn=64);}",
                scadString.content());
        Assertions.assertTrue(cone.get().isConeTube());
        Assertions.assertTrue(cone.get().isCone());
        Assertions.assertFalse(cone.get().isCylinderTube());
        Assertions.assertFalse(cone.get().isCylinder());
    }

    @Test
    void test_coneTubeFragment() {
        Shape<Cone> cone = shapelist.get(8);
        ScadString scadString = cone.generate();
        Assertions.assertEquals(
                "difference(){cylinder(h=100.0000,d1=50.0000,d2=35.0000,$fn=6);cylinder(h=100.0000,d1=25.0000,d2=10.0000,$fn=3);}",
                scadString.content());
        Assertions.assertTrue(cone.get().isConeTube());
        Assertions.assertTrue(cone.get().isCone());
        Assertions.assertFalse(cone.get().isCylinderTube());
        Assertions.assertFalse(cone.get().isCylinder());
    }

    @Test
    void test_cylinder() {
        Shape<Cone> cone = shapelist.get(1);
        ScadString scadString = cone.generate();
        Assertions.assertEquals("cylinder(h=100.0000,d=50.0000,$fn=64);", scadString.content());
        Assertions.assertFalse(cone.get().isConeTube());
        Assertions.assertFalse(cone.get().isCone());
        Assertions.assertFalse(cone.get().isCylinderTube());
        Assertions.assertTrue(cone.get().isCylinder());
    }

    @Test
    void test_cylinderConeTube() {
        Shape<Cone> cone = shapelist.get(3);
        ScadString scadString = cone.generate();
        Assertions.assertEquals(
                "difference(){cylinder(h=100.0000,d1=50.0000,d2=50.0000,$fn=64);cylinder(h=100.0000,d1=25.0000,d2=35.0000,$fn=64);}",
                scadString.content());
        Assertions.assertTrue(cone.get().isConeTube());
        Assertions.assertFalse(cone.get().isCone());
        Assertions.assertFalse(cone.get().isCylinderTube());
        Assertions.assertTrue(cone.get().isCylinder());
    }

    @Test
    void test_cylinderTube() {
        Shape<Cone> cone = shapelist.get(2);
        ScadString scadString = cone.generate();
        Assertions.assertEquals(
                "difference(){cylinder(h=100.0000,d=50.0000,$fn=64);cylinder(h=100.0000,d=25.0000,$fn=64);}",
                scadString.content());
        Assertions.assertFalse(cone.get().isConeTube());
        Assertions.assertFalse(cone.get().isCone());
        Assertions.assertTrue(cone.get().isCylinderTube());
        Assertions.assertTrue(cone.get().isCylinder());
    }

    @Test
    void test_default_values() {
        ScadString scadString = shapelist.get(0).generate();
        Assertions.assertEquals("cylinder(h=100.0000,d=100.0000,$fn=64);", scadString.content());
    }

    @Test
    void test_height() {
        ScadString scadString = shapelist.get(7).generate();
        Assertions.assertEquals("cylinder(h=80.0000,d=100.0000,$fn=64);", scadString.content());
    }

    @Test
    void test_position() {
        ScadString scadString = shapelist.get(9).generate();
        Assertions.assertEquals("translate([1.0,2.0,3.0]){cylinder(h=100.0000,d=100.0000,$fn=64);}",
                scadString.content());
    }

}