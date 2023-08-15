package com.openscadgenerator.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Tupel3D;

class Tupel3DServiceTest {

    @Test
    void maxDiameterVector() {
        List<Tupel3D> points = Arrays.asList(
                Tupel3D.ORIGIN,
                new Tupel3D(10, 0, 0),
                new Tupel3D(10, 10, 0),
                new Tupel3D(0, 10, 0),
                new Tupel3D(0, 0, 10),
                new Tupel3D(10, 10, 10)
        );
        Tupel3D expectedVector = new Tupel3D(20,20,20);
        Tupel3D actualVector =Tupel3DService.maxDiameterVector(points);
        Assertions.assertEquals(expectedVector,actualVector);
    }
}