package com.openscadgenerator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.model.Point3D;

class EuklidUtilTest {

    @Test
    void getDistance() {
        Point3D point1 = new Point3D().x(3).y(4).z(5);
        Point3D point2 = new Point3D().x(-3).y(-4).z(5);
        double distance1 = EuklidUtil.getDistance(point1, point2);
        Assertions.assertEquals(10.0, distance1);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    void getNearestPoint() {
        Point3D origin = Point3D.ORIGIN;
        Point3D point1 = new Point3D().x(3).y(4).z(5);
        Point3D point2 = new Point3D().x(-3).y(-4).z(5);
        Point3D point3 = new Point3D().x(2).y(4).z(4);
        List<Point3D> point3DList = Arrays.asList(origin, point1, point2, point3);
        Optional<Point3D> nearestPoint1 = EuklidUtil.getNearestPoint(new Point3D().x(5), point3DList);
        Optional<Point3D> nearestPoint2 = EuklidUtil.getNearestPoint(new Point3D().y(5), point3DList);
        Optional<Point3D> nearestPoint3 = EuklidUtil.getNearestPoint(new Point3D().z(5), point3DList);
        Assertions.assertEquals(origin, nearestPoint1.get());
        Assertions.assertEquals(point3, nearestPoint2.get());
        Assertions.assertEquals(point3, nearestPoint3.get());
        Assertions.assertTrue(EuklidUtil.getNearestPoint(new Point3D().z(5), new ArrayList<>()).isEmpty());
    }

    @Test
    void getOriginDistance() {
        Point3D point1 = new Point3D().x(2).y(4).z(4);
        double distance1 = EuklidUtil.getOriginDistance(point1);
        Assertions.assertEquals(6.0, distance1);
    }
}