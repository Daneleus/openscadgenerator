package com.openscadgenerator.shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.number.Greater2IntegerNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.service.FileService;

class CylinderTest {

    @Test
    void defaultValues() {
        ScadString scadString = new Cylinder().generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "cylinderDefault");
        Assertions.assertEquals("cylinder(h=100.0000,d=100.0000,$fn=100);", scadString.content());
    }

    @Test
    void diameter() {
        ScadString scadString = new Cylinder().diameter(new PositiveDecimalNumber(50)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "cylinderDiameter");
        Assertions.assertEquals("cylinder(h=100.0000,d=50.0000,$fn=100);", scadString.content());
    }

    @Test
    void fragments() {
        ScadString scadString = new Cylinder().fragments(new Greater2IntegerNumber(3)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "cylinderFragments");
        Assertions.assertEquals("cylinder(h=100.0000,d=100.0000,$fn=3);", scadString.content());
    }

    @Test
    void height() {
        ScadString scadString = new Cylinder().height(new PositiveDecimalNumber(50)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "cylinderHeight");
        Assertions.assertEquals("cylinder(h=50.0000,d=100.0000,$fn=100);", scadString.content());
    }

    @Test
    void test_isInvalid() {
        Assertions.assertFalse(new Cylinder().isInvalid());
    }

}