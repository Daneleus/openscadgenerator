package com.openscadgenerator.util;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Tupel3D;

class EuklidUtilTest {

    @Test
    void test_getDistance() {
        Tupel3D point1 = new Tupel3D(3, 4, 5);
        Tupel3D point2 = new Tupel3D(-3, -4, 5);
        double distance1 = EuklidUtil.getDistance(point1, point2);
        Assertions.assertEquals(10.0, distance1);
    }

    @Test
    void test_getDistanceXYZ() {
        Tupel3D origin = Tupel3D.ORIGIN;
        Tupel3D point1 = new Tupel3D(1, 2, 3);
        Tupel3D point2 = new Tupel3D(2, 2, 3);
        Tupel3D point3 = new Tupel3D(2, 3, 3);
        Tupel3D point4 = new Tupel3D(2, 3, 4);
        Assertions.assertEquals(EuklidUtil.getDistanceX(origin, point1), EuklidUtil.getDistanceX(origin, point2));
        Assertions.assertEquals(EuklidUtil.getDistanceY(origin, point2), EuklidUtil.getDistanceY(origin, point3));
        Assertions.assertEquals(EuklidUtil.getDistanceZ(origin, point3), EuklidUtil.getDistanceZ(origin, point4));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent") @Test void test_getNearestPoint() {
        Tupel3D origin = Tupel3D.ORIGIN;
        Tupel3D point1 = new Tupel3D(100, 0, 0);
        Tupel3D point2 = new Tupel3D(0, 100, 0);
        Tupel3D point3 = new Tupel3D(0, 0, 100);
        Tupel3D point4 = new Tupel3D(1, 1, 1);
        List<Tupel3D> points = Arrays.asList(point1, point2, point3, point4);
        Assertions.assertEquals(EuklidUtil.getNearestPointX(origin, points).get(), point1);
        Assertions.assertEquals(EuklidUtil.getNearestPointY(origin, points).get(), point2);
        Assertions.assertEquals(EuklidUtil.getNearestPointZ(origin, points).get(), point3);
        Assertions.assertEquals(EuklidUtil.getNearestPoint(origin, points).get(), point4);
    }

    @Test
    void test_getOriginDistance() {
        Tupel3D point1 = new Tupel3D(2, 4, 4);
        double distance1 = EuklidUtil.getOriginDistance(point1);
        Assertions.assertEquals(6.0, distance1);
    }
}