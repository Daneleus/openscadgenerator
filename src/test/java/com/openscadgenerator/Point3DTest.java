package com.openscadgenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.model.Point3D;

class Point3DTest {

    @Test
    void test_isOrigin() {
        Assertions.assertTrue(new Point3D().isOrigin());
        Assertions.assertTrue(Point3D.ORIGIN.isOrigin());
        Assertions.assertTrue(new Point3D().x(0).y(0).z(0).isOrigin());
        Assertions.assertFalse(new Point3D().x(1).y(-1).z(0).isOrigin());
    }
}