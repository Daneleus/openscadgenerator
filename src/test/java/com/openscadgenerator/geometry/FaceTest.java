package com.openscadgenerator.geometry;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FaceTest {

    @Test
    void equals() {
        Assertions.assertEquals(new Face(Arrays.asList(1, 2, 3)), new Face(Arrays.asList(1, 2, 3)));
        Assertions.assertNotEquals(new Face(Arrays.asList(0, 1, 2, 3)), new Face(Arrays.asList(1, 2, 3)));
        Assertions.assertNotEquals(new Face(Arrays.asList(1, 2, 3)), new Face(Arrays.asList(1, 2, 3, 4)));
        Assertions.assertNotEquals(new Face(Arrays.asList(2, 3)), new Face(Arrays.asList(1, 2, 3)));
        Assertions.assertNotEquals(new Face(Arrays.asList(1, 2, 3)), new Face(Arrays.asList(1, 2)));
        Assertions.assertNotEquals(new Face(Arrays.asList(1, 2, 3)), new Tupel3D(1, 2, 3));
    }

    @Test
    void to_String() {
        Assertions.assertEquals("[1,2,3]", new Face(Arrays.asList(1, 2, 3)).toString());
    }

}