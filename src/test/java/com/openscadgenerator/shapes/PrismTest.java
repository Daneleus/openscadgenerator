package com.openscadgenerator.shapes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.model.Diameter;
import com.openscadgenerator.model.Fragments;
import com.openscadgenerator.model.Height;
import com.openscadgenerator.model.Point2D;
import com.openscadgenerator.model.Point3D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;
import com.openscadgenerator.util.ScadUtil;

class PrismTest {

    static List<Shape<Prism>> shapelist = new ArrayList<>();

    @AfterAll
    static void generateTestFile() {
        double[] xPos = { 0 };
        ScadUtil.generateScadFile(
                shapelist.stream().map(shape -> shape.position(new Point3D().x(xPos[0] += 100)))
                        .collect(Collectors.toList()), "src\\test\\samples", "prismTest.scad");
    }

    @BeforeAll
    static void initShapes() {
        shapelist.add(new Prism());
        shapelist.add(new Prism().points(
                Arrays.asList(Point2D.ORIGIN, new Point2D().x(5), new Point2D().x(13).y(13), new Point2D().y(7))));
        shapelist.add(new Prism().position(new Point3D().x(1).y(2).z(3)));
        shapelist.add(new Prism().diameter(new Diameter().value(35)).fragments(new Fragments().value(7)));
        shapelist.add(new Prism().height(new Height().value(35)));
    }

    @Test
    void test_arbitraryPoints() {
        ScadString scadString = shapelist.get(1).generate();
        Assertions.assertEquals(
                "linear_extrude(100.0000){polygon(points=[[0.0,0.0],[5.0,0.0],[13.0,13.0],[0.0,7.0]]);}",
                scadString.content());
    }

    @Test
    void test_height() {
        ScadString scadString = shapelist.get(4).generate();
        Assertions.assertEquals("linear_extrude(35.0000){polygon(points=[[10.0,0.0],[0.0,10.0],[0.0,0.0]]);}",
                scadString.content());
    }

    @Test
    void test_isInvalid() {
        Assertions.assertFalse(new Prism().isInvalid());
        Assertions.assertTrue(new Prism().points(new ArrayList<>()).diameter(new Diameter().value(0)).isInvalid());
        Assertions.assertTrue(new Prism().points(Arrays.asList(new Point2D().x(1), new Point2D().y(1)))
                .diameter(new Diameter().value(0)).isInvalid());
        Assertions.assertTrue(new Prism().points(Arrays.asList(new Point2D().x(1), new Point2D().y(1), new Point2D()))
                .diameter(new Diameter().value(0)).isInvalid());
        Assertions.assertFalse(new Prism().points(Arrays.asList(new Point2D().x(1), new Point2D().y(1), new Point2D()))
                .isInvalid());
        Assertions.assertFalse(new Prism().diameter(new Diameter()).isInvalid());
    }

    @Test
    void test_nprism() {
        ScadString scadString = shapelist.get(3).generate();
        Assertions.assertEquals("linear_extrude(100.0000){circle(d=35.0000, $fn=7);}",
                scadString.content());
    }

    @Test
    void test_position() {
        ScadString scadString = shapelist.get(2).generate();
        Assertions.assertEquals(
                "translate([1.0,2.0,3.0]){linear_extrude(100.0000){polygon(points=[[10.0,0.0],[0.0,10.0],[0.0,0.0]]);}}",
                scadString.content());
    }

    @Test
    void test_prism() {
        ScadString scadString = shapelist.get(0).generate();
        Assertions.assertEquals("linear_extrude(100.0000){polygon(points=[[10.0,0.0],[0.0,10.0],[0.0,0.0]]);}",
                scadString.content());
    }
}