package com.openscadgenerator.geometry;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FaceTest {

    @Test
    void to_String() {
        Assertions.assertEquals("[1,2,3]", new Face(Arrays.asList(1, 2, 3)).toString());
    }
}