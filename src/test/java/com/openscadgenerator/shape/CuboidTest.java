package com.openscadgenerator.shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.util.ScadUtil;

class CuboidTest {

    @Test
    void defaultValues() {
        ScadString scadString = new Cuboid().generate();
        ScadUtil.generateScadFile(scadString, "src\\test\\samples", "cuboid_default.scad");
        Assertions.assertEquals("cube(size=[100.0000,50.0000,200.0000],center=true);", scadString.content());
    }

    @Test
    void isInvalid() {
        Assertions.assertFalse(new Cone().isInvalid());
    }

    @Test
    void xLength() {
        ScadString scadString = new Cuboid().xLength(new PositiveDecimalNumber(10)).generate();
        ScadUtil.generateScadFile(scadString, "src\\test\\samples", "cuboid_xLength.scad");
        Assertions.assertEquals("cube(size=[10.0000,50.0000,200.0000],center=true);", scadString.content());
    }

    @Test
    void yLength() {
        ScadString scadString = new Cuboid().yLength(new PositiveDecimalNumber(10)).generate();
        ScadUtil.generateScadFile(scadString, "src\\test\\samples", "cuboid_yLength.scad");
        Assertions.assertEquals("cube(size=[100.0000,10.0000,200.0000],center=true);", scadString.content());
    }

    @Test
    void zLength() {
        ScadString scadString = new Cuboid().zLength(new PositiveDecimalNumber(10)).generate();
        ScadUtil.generateScadFile(scadString, "src\\test\\samples", "cuboid_zLength.scad");
        Assertions.assertEquals("cube(size=[100.0000,50.0000,10.0000],center=true);", scadString.content());
    }
}