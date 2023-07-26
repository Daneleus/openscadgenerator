package com.openscadgenerator.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Tupel2DTest {

    @Test
    void isOrigin() {
        Assertions.assertTrue(Tupel2D.ORIGIN.isOrigin());
        Assertions.assertTrue(new Tupel2D(0, 0).isOrigin());
        Assertions.assertFalse(new Tupel2D(1, -1).isOrigin());
    }

    @Test
    void to_String() {
        Assertions.assertEquals("[1.0,2.0]", new Tupel2D(1, 2).toString());
    }
}