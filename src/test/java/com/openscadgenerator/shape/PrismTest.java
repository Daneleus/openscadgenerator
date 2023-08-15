package com.openscadgenerator.shape;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Tupel2D;
import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.service.FileService;

class PrismTest {

    @Test
    void defaultValues() {
        ScadString scadString = new Prism().generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "prismDefault");
        Assertions.assertEquals(
                "linear_extrude(100.0000){polygon(points=[[100.0,0.0],[0.0,100.0],[-10.0,0.0],[0.0,-100.0]]);}",
                scadString.content());
    }

    @Test
    void height() {
        ScadString scadString = new Prism().height(new PositiveDecimalNumber(50)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "prismHeight");
        Assertions.assertEquals(
                "linear_extrude(50.0000){polygon(points=[[100.0,0.0],[0.0,100.0],[-10.0,0.0],[0.0,-100.0]]);}",
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
                        Arrays.asList(Tupel2D.ORIGIN, new Tupel2D(50, 0), new Tupel2D(130, 130), new Tupel2D(0, 70)))
                .generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "prismPoints");
        Assertions.assertEquals(
                "linear_extrude(100.0000){polygon(points=[[0.0,0.0],[50.0,0.0],[130.0,130.0],[0.0,70.0]]);}",
                scadString.content());
    }

    @Test
    void getCenter() {
        Assertions.assertEquals(new Tupel3D(0,0,50), new Prism().getCenter());
    }
}