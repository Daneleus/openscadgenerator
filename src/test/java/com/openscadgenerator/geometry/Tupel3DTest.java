package com.openscadgenerator.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Tupel3DTest {

    @Test
    void isOrigin() {
        Assertions.assertTrue(Tupel3D.ORIGIN.isOrigin());
        Assertions.assertTrue(new Tupel3D(0, 0, 0).isOrigin());
        Assertions.assertFalse(new Tupel3D(1, -1, 0).isOrigin());
    }

    @Test
    void to_String() {
        Assertions.assertEquals("[1.0,2.0,3.0]", new Tupel3D(1, 2, 3).toString());
    }
}