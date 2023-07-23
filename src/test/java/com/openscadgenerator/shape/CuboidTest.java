package com.openscadgenerator.shape;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.model.Length;
import com.openscadgenerator.model.Point3D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.util.ScadUtil;

class CuboidTest {

    static List<Shape<Cuboid>> shapelist = new ArrayList<>();

    @AfterAll
    static void generateTestFile() {
        double[] xPos = { 0 };
        ScadUtil.generateScadFile(
                shapelist.stream().map(shape -> shape.position(new Point3D().x(xPos[0] += 100)))
                        .collect(Collectors.toList()), "src\\test\\samples", "cuboidTest.scad");
    }

    @BeforeAll
    static void initShapes() {
        shapelist.add(new Cuboid().xLength(new Length().value(10)).yLength(new Length().value(20))
                .zLength(new Length().value(40)));
        shapelist.add(new Cuboid().position(new Point3D().x(1).y(2).z(3)));
        shapelist.add(new Cuboid().cube(new Length().value(25)));
    }

    @Test
    void test_cube() {
        ScadString scadString = shapelist.get(2).generate();
        Assertions.assertEquals("cube(size=[25.0000,25.0000,25.0000],center=true);", scadString.content());
    }

    @Test
    void test_cuboid() {
        ScadString scadString = shapelist.get(0).generate();
        Assertions.assertEquals("cube(size=[10.0000,20.0000,40.0000],center=true);", scadString.content());
    }

    @Test
    void test_position() {
        ScadString scadString = shapelist.get(1).generate();
        Assertions.assertEquals("translate([1.0,2.0,3.0]){cube(size=[100.0000,100.0000,100.0000],center=true);}",
                scadString.content());
    }

    @Test
    void test_isInvalid() {
        Cuboid cuboid = new Cuboid();
        Assertions.assertFalse(cuboid.isInvalid());
        Cuboid cuboidX = new Cuboid().xLength(new Length().value(0));
        Assertions.assertTrue(cuboidX.isInvalid());
        Cuboid cuboidY = new Cuboid().yLength(new Length().value(0));
        Assertions.assertTrue(cuboidY.isInvalid());
        Cuboid cuboidZ = new Cuboid().zLength(new Length().value(0));
        Assertions.assertTrue(cuboidZ.isInvalid());
    }

    @Test
    void test_generate_invalid() {
        Assertions.assertThrows(RuntimeException.class, () -> new Cuboid().xLength(new Length().value(0)).generate());
    }
}