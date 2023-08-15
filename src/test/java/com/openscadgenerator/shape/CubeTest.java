package com.openscadgenerator.shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.service.FileService;

class CubeTest {

    @Test
    void defaultValues() {
        ScadString scadString = new Cube().generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "cubeDefault");
        Assertions.assertEquals("cube(size=100.0000,center=true);", scadString.content());
    }

    @Test
    void getCenter() {
        Assertions.assertEquals(Tupel3D.ORIGIN, new Cube().getCenter());
    }

    @Test
    void isInvalid() {
        Assertions.assertFalse(new Cone().isInvalid());
    }

    @Test
    void length() {
        ScadString scadString = new Cube().length(new PositiveDecimalNumber(50)).generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "cubeLength");
        Assertions.assertEquals("cube(size=50.0000,center=true);", scadString.content());
    }
}