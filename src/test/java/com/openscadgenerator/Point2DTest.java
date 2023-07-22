package com.openscadgenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.model.Point2D;

class Point2DTest {

    @Test
    void test_isOrigin() {
        Assertions.assertTrue(new Point2D().isOrigin());
        Assertions.assertTrue(Point2D.ORIGIN.isOrigin());
        Assertions.assertTrue(new Point2D().x(0).y(0).isOrigin());
        Assertions.assertFalse(new Point2D().x(1).y(-1).isOrigin());
    }
}