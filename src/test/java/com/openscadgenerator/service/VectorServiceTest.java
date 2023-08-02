package com.openscadgenerator.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Tupel3D;

class VectorServiceTest {

    @Test
    void crossProduct() {
        Tupel3D a = new Tupel3D(1, 2, 3);
        Tupel3D b = new Tupel3D(0, 4, 7);
        Tupel3D axb = new Tupel3D(2, -7, 4);
        Tupel3D bxa = new Tupel3D(-2, 7, -4);
        Tupel3D axa = new Tupel3D(0, 0, 0);
        Tupel3D bxb = new Tupel3D(0, 0, 0);
        Assertions.assertEquals(axb, VectorService.crossProduct(a, b));
        Assertions.assertEquals(bxa, VectorService.crossProduct(b, a));
        Assertions.assertEquals(axa, VectorService.crossProduct(a, a));
        Assertions.assertEquals(bxb, VectorService.crossProduct(b, b));
    }

    @Test
    void dotProduct() {
        Tupel3D a = new Tupel3D(1, 2, 3);
        Tupel3D b = new Tupel3D(0, 4, 7);
        double ab = 29;
        double ba = 29;
        double aa = 14;
        double bb = 65;
        Assertions.assertEquals(ba, VectorService.dotProduct(a, b));
        Assertions.assertEquals(ab, VectorService.dotProduct(b, a));
        Assertions.assertEquals(aa, VectorService.dotProduct(a, a));
        Assertions.assertEquals(bb, VectorService.dotProduct(b, b));
        Assertions.assertEquals(0, VectorService.dotProduct(a, Tupel3D.ORIGIN));
        Assertions.assertEquals(0, VectorService.dotProduct(b, Tupel3D.ORIGIN));
    }

    @Test
    void getDistance() {
        Tupel3D point1 = new Tupel3D(3, 4, 5);
        Tupel3D point2 = new Tupel3D(-3, -4, 5);
        double distance1 = VectorService.getDistance(point1, point2);
        Assertions.assertEquals(10.0, distance1);
    }

    @Test
    void getDistanceXYZ() {
        Tupel3D origin = Tupel3D.ORIGIN;
        Tupel3D point1 = new Tupel3D(1, 2, 3);
        Tupel3D point2 = new Tupel3D(2, 2, 3);
        Tupel3D point3 = new Tupel3D(2, 3, 3);
        Tupel3D point4 = new Tupel3D(2, 3, 4);
        Assertions.assertEquals(VectorService.getDistanceX(origin, point1), VectorService.getDistanceX(origin, point2));
        Assertions.assertEquals(VectorService.getDistanceY(origin, point2), VectorService.getDistanceY(origin, point3));
        Assertions.assertEquals(VectorService.getDistanceZ(origin, point3), VectorService.getDistanceZ(origin, point4));
    }

    @Test
    void getFacePointDistance() {
        Tupel3D x = new Tupel3D(0, 0, 0);
        Tupel3D y = new Tupel3D(1, 0, 0);
        Tupel3D z = new Tupel3D(0, 1, 0);
        Tupel3D a = new Tupel3D(0, 0, 10);
        Tupel3D b = new Tupel3D(0, 15, 0);
        Tupel3D c = new Tupel3D(5, 0, 0);
        Tupel3D d = new Tupel3D(0, 0, -4);
        Assertions.assertEquals(10, VectorService.getFacePointDistance(x, y, z, a));
        Assertions.assertEquals(0, VectorService.getFacePointDistance(x, y, z, b));
        Assertions.assertEquals(0, VectorService.getFacePointDistance(x, y, z, c));
        Assertions.assertEquals(-4, VectorService.getFacePointDistance(x, y, z, d));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    void getNearestPoint() {
        Tupel3D origin = Tupel3D.ORIGIN;
        Tupel3D point1 = new Tupel3D(100, 0, 0);
        Tupel3D point2 = new Tupel3D(0, 100, 0);
        Tupel3D point3 = new Tupel3D(0, 0, 100);
        Tupel3D point4 = new Tupel3D(1, 1, 1);
        List<Tupel3D> points = Arrays.asList(point1, point2, point3, point4);
        Assertions.assertEquals(VectorService.getNearestPointX(origin, points).get(), point1);
        Assertions.assertEquals(VectorService.getNearestPointY(origin, points).get(), point2);
        Assertions.assertEquals(VectorService.getNearestPointZ(origin, points).get(), point3);
        Assertions.assertEquals(VectorService.getNearestPoint(origin, points).get(), point4);
    }

    @Test
    void getOriginDistance() {
        Tupel3D point1 = new Tupel3D(2, 4, 4);
        double distance1 = VectorService.getOriginDistance(point1);
        Assertions.assertEquals(6.0, distance1);
    }

    @Test
    void normVector() {
        Tupel3D x = new Tupel3D(4, 0, 0);
        Tupel3D y = new Tupel3D(0, 10, 0);
        Tupel3D z = new Tupel3D(0, 0, 15);
        Tupel3D ex = new Tupel3D(1, 0, 0);
        Tupel3D ey = new Tupel3D(0, 1, 0);
        Tupel3D ez = new Tupel3D(0, 0, 1);
        Assertions.assertEquals(ex, VectorService.normVector(x));
        Assertions.assertEquals(ey, VectorService.normVector(y));
        Assertions.assertEquals(ez, VectorService.normVector(z));
        Assertions.assertThrows(IllegalArgumentException.class, () -> VectorService.normVector(Tupel3D.ORIGIN),
                "vector has length 0!");
    }

    @Test
    void vectorAddition() {
        Tupel3D a = new Tupel3D(1, 2, 3);
        Tupel3D b = new Tupel3D(0, 4, 7);
        Tupel3D ab = new Tupel3D(1, 6, 10);
        Tupel3D aa = new Tupel3D(2, 4, 6);
        Tupel3D bb = new Tupel3D(0, 8, 14);
        Assertions.assertEquals(ab, VectorService.vectorAddition(a, b));
        Assertions.assertEquals(ab, VectorService.vectorAddition(b, a));
        Assertions.assertEquals(aa, VectorService.vectorAddition(a, a));
        Assertions.assertEquals(bb, VectorService.vectorAddition(b, b));
    }

    @Test
    void vectorSubtraction() {
        Tupel3D a = new Tupel3D(1, 2, 3);
        Tupel3D b = new Tupel3D(0, 4, 7);
        Tupel3D ab = new Tupel3D(-1, 2, 4);
        Tupel3D ba = new Tupel3D(1, -2, -4);
        Tupel3D aa = new Tupel3D(0, 0, 0);
        Tupel3D bb = new Tupel3D(0, 0, 0);
        Assertions.assertEquals(ba, VectorService.vectorSubtraction(a, b));
        Assertions.assertEquals(ab, VectorService.vectorSubtraction(b, a));
        Assertions.assertEquals(aa, VectorService.vectorSubtraction(a, a));
        Assertions.assertEquals(bb, VectorService.vectorSubtraction(b, b));
    }

    @Test
    void vectorTimesScalar() {
        Tupel3D v = new Tupel3D(1, 2, 3);
        Tupel3D b = new Tupel3D(5, 10, 15);
        double x = 5;
        double y = 1;
        double z = 0;
        Assertions.assertEquals(b, VectorService.vectorTimesScalar(v, x));
        Assertions.assertEquals(v, VectorService.vectorTimesScalar(v, y));
        Assertions.assertEquals(Tupel3D.ORIGIN, VectorService.vectorTimesScalar(v, z));
        Assertions.assertEquals(Tupel3D.ORIGIN, VectorService.vectorTimesScalar(Tupel3D.ORIGIN, x));
        Assertions.assertEquals(Tupel3D.ORIGIN, VectorService.vectorTimesScalar(Tupel3D.ORIGIN, z));
    }
}