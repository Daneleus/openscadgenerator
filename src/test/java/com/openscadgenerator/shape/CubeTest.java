package com.openscadgenerator.shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.util.FileUtil;

class CubeTest {

    @Test
    void defaultValues() {
        ScadString scadString = new Cube().generate();
        FileUtil.writeScadStringToFile(scadString, "src\\test\\samples", "cube_default");
        Assertions.assertEquals("cube(size=100.0000,center=true);", scadString.content());
    }

    @Test
    void isInvalid() {
        Assertions.assertFalse(new Cone().isInvalid());
    }

    @Test
    void length() {
        ScadString scadString = new Cube().length(new PositiveDecimalNumber(50)).generate();
        FileUtil.writeScadStringToFile(scadString, "src\\test\\samples", "cube_Length");
        Assertions.assertEquals("cube(size=50.0000,center=true);", scadString.content());
    }
}