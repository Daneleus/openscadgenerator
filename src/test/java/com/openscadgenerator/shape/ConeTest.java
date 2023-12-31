package com.openscadgenerator.shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.number.Greater2IntegerNumber;
import com.openscadgenerator.number.NotNegativeDecimalNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.service.FileService;

class ConeTest {

    @Test
    void defaultValues() {
        ScadString scadString = new Cone().generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "coneDefault");
        Assertions.assertEquals("cylinder(h=100.0000,d1=100.0000,d2=0.0000,$fn=100);", scadString.content());
    }

    @Test
    void diameterBottom() {
        ScadString scadString = new Cone().diameterBottom(new NotNegativeDecimalNumber(50)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "coneDiameterBottom");
        Assertions.assertEquals("cylinder(h=100.0000,d1=50.0000,d2=0.0000,$fn=100);", scadString.content());
    }

    @Test
    void diameterTop() {
        ScadString scadString = new Cone().diameterTop(new NotNegativeDecimalNumber(50)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "coneDiameterTop");
        Assertions.assertEquals("cylinder(h=100.0000,d1=100.0000,d2=50.0000,$fn=100);", scadString.content());
    }

    @Test
    void getCenter() {
        Assertions.assertEquals(new Tupel3D(0,0,50), new Cone().getCenter());
    }

    @Test
    void fragments() {
        ScadString scadString = new Cone().fragments(new Greater2IntegerNumber(3)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "coneFragments");
        Assertions.assertEquals("cylinder(h=100.0000,d1=100.0000,d2=0.0000,$fn=3);", scadString.content());
    }

    @Test
    void height() {
        ScadString scadString = new Cone().height(new PositiveDecimalNumber(50)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "coneHeight");
        Assertions.assertEquals("cylinder(h=50.0000,d1=100.0000,d2=0.0000,$fn=100);", scadString.content());
    }

    @Test
    void isInvalid() {
        Assertions.assertFalse(new Cone().isInvalid());
        Assertions.assertTrue(
                new Cone().diameterBottom(new NotNegativeDecimalNumber(0)).diameterTop(new NotNegativeDecimalNumber(0))
                        .isInvalid());
    }

}