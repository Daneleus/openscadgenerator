package com.openscadgenerator.shape;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Tupel2D;
import com.openscadgenerator.number.Greater2IntegerNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.service.FileService;

class PrismTest {

    @Test
    void defaultValues() {
        ScadString scadString = new Prism().generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "prism_dafault");
        Assertions.assertEquals("linear_extrude(100.0000){circle(d=100.0000, $fn=100);}",
                scadString.content());
    }

    @Test
    void diameter() {
        ScadString scadString = new Prism().diameter(new PositiveDecimalNumber(50)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "prism_diameter");
        Assertions.assertEquals("linear_extrude(100.0000){circle(d=50.0000, $fn=100);}",
                scadString.content());
    }

    @Test
    void fragments() {
        ScadString scadString = new Prism().fragments(new Greater2IntegerNumber(3)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "prism_fragments");
        Assertions.assertEquals("linear_extrude(100.0000){circle(d=100.0000, $fn=3);}", scadString.content());
    }

    @Test
    void height() {
        ScadString scadString = new Prism().height(new PositiveDecimalNumber(50)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "prism_height");
        Assertions.assertEquals("linear_extrude(50.0000){circle(d=100.0000, $fn=100);}",
                scadString.content());
    }

    @Test
    void isInvalid() {
        //TODO 3 Points in Line
        Assertions.assertFalse(new Prism().isInvalid());
    }

    @Test
    void points() {
        ScadString scadString = new Prism().points(
                Arrays.asList(Tupel2D.ORIGIN, new Tupel2D(5, 0), new Tupel2D(13, 13), new Tupel2D(0, 7))).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "prism_points");
        Assertions.assertEquals(
                "linear_extrude(100.0000){polygon(points=[[0.0,0.0],[5.0,0.0],[13.0,13.0],[0.0,7.0]]);}",
                scadString.content());
    }
}