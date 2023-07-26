package com.openscadgenerator.shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.number.Greater2IntegerNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.util.ScadUtil;

class CylinderTest {

    @Test
    void defaultValues() {
        ScadString scadString = new Cylinder().generate();
        ScadUtil.generateScadFile(scadString, "src\\test\\samples", "cylinder_default.scad");
        Assertions.assertEquals("cylinder(h=100.0000,d=100.0000,$fn=100);", scadString.content());
    }

    @Test
    void diameter() {
        ScadString scadString = new Cylinder().diameter(new PositiveDecimalNumber(50)).generate();
        ScadUtil.generateScadFile(scadString, "src\\test\\samples", "cylinder_diameter.scad");
        Assertions.assertEquals("cylinder(h=100.0000,d=50.0000,$fn=100);", scadString.content());
    }

    @Test
    void fragments() {
        ScadString scadString = new Cylinder().fragments(new Greater2IntegerNumber(3)).generate();
        ScadUtil.generateScadFile(scadString, "src\\test\\samples", "cylinder_fragments.scad");
        Assertions.assertEquals("cylinder(h=100.0000,d=100.0000,$fn=3);", scadString.content());
    }

    @Test
    void height() {
        ScadString scadString = new Cylinder().height(new PositiveDecimalNumber(50)).generate();
        ScadUtil.generateScadFile(scadString, "src\\test\\samples", "cylinder_height.scad");
        Assertions.assertEquals("cylinder(h=50.0000,d=100.0000,$fn=100);", scadString.content());
    }

    @Test
    void test_isInvalid() {
        Assertions.assertFalse(new Cylinder().isInvalid());
    }

}