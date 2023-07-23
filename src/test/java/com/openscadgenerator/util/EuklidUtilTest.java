package com.openscadgenerator.util;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.model.Point3D;

class EuklidUtilTest {

    @Test
    void test_getDistance() {
        Point3D point1 = new Point3D().x(3).y(4).z(5);
        Point3D point2 = new Point3D().x(-3).y(-4).z(5);
        double distance1 = EuklidUtil.getDistance(point1, point2);
        Assertions.assertEquals(10.0, distance1);
    }

    @Test
    void test_getDistanceXYZ() {
        Point3D origin = Point3D.ORIGIN;
        Point3D point1 = new Point3D().x(1).y(2).z(3);
        Point3D point2 = new Point3D().x(2).y(2).z(3);
        Point3D point3 = new Point3D().x(2).y(3).z(3);
        Point3D point4 = new Point3D().x(2).y(3).z(4);
        Assertions.assertEquals(EuklidUtil.getDistanceX(origin, point1), EuklidUtil.getDistanceX(origin, point2));
        Assertions.assertEquals(EuklidUtil.getDistanceY(origin, point2), EuklidUtil.getDistanceY(origin, point3));
        Assertions.assertEquals(EuklidUtil.getDistanceZ(origin, point3), EuklidUtil.getDistanceZ(origin, point4));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent") @Test void test_getNearestPoint() {
        Point3D origin = Point3D.ORIGIN;
        Point3D point1 = new Point3D().x(100).y(0).z(0);
        Point3D point2 = new Point3D().x(0).y(100).z(0);
        Point3D point3 = new Point3D().x(0).y(0).z(100);
        Point3D point4 = new Point3D().x(1).y(1).z(1);
        List<Point3D> points = Arrays.asList(point1, point2, point3, point4);
        Assertions.assertEquals(EuklidUtil.getNearestPointX(origin, points).get(), point1);
        Assertions.assertEquals(EuklidUtil.getNearestPointY(origin, points).get(), point2);
        Assertions.assertEquals(EuklidUtil.getNearestPointZ(origin, points).get(), point3);
        Assertions.assertEquals(EuklidUtil.getNearestPoint(origin, points).get(), point4);
    }

    @Test
    void test_getOriginDistance() {
        Point3D point1 = new Point3D().x(2).y(4).z(4);
        double distance1 = EuklidUtil.getOriginDistance(point1);
        Assertions.assertEquals(6.0, distance1);
    }
}