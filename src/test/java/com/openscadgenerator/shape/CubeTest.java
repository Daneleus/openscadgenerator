package com.openscadgenerator.shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.util.ScadUtil;

class CubeTest {

    @Test
    void defaultValues() {
        ScadString scadString = new Cube().generate();
        ScadUtil.generateScadFile(scadString, "src\\test\\samples", "cube_default.scad");
        Assertions.assertEquals("cube(size=100.0000,center=true);", scadString.content());
    }

    @Test
    void isInvalid() {
        Assertions.assertFalse(new Cone().isInvalid());
    }

    @Test
    void length() {
        ScadString scadString = new Cube().length(new PositiveDecimalNumber(50)).generate();
        ScadUtil.generateScadFile(scadString, "src\\test\\samples", "cube_Length.scad");
        Assertions.assertEquals("cube(size=50.0000,center=true);", scadString.content());
    }
}