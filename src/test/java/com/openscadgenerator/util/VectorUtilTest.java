package com.openscadgenerator.util;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.geometry.Tupel3D;

class VectorUtilTest {

    @Test
    void crossProduct() {
        Tupel3D a = new Tupel3D(1, 2, 3);
        Tupel3D b = new Tupel3D(0, 4, 7);
        Tupel3D axb = new Tupel3D(2, -7, 4);
        Tupel3D bxa = new Tupel3D(-2, 7, -4);
        Tupel3D axa = new Tupel3D(0, 0, 0);
        Tupel3D bxb = new Tupel3D(0, 0, 0);
        Assertions.assertEquals(axb, VectorUtil.crossProduct(a, b));
        Assertions.assertEquals(bxa, VectorUtil.crossProduct(b, a));
        Assertions.assertEquals(axa, VectorUtil.crossProduct(a, a));
        Assertions.assertEquals(bxb, VectorUtil.crossProduct(b, b));
    }

    @Test
    void dotProduct() {
        Tupel3D a = new Tupel3D(1, 2, 3);
        Tupel3D b = new Tupel3D(0, 4, 7);
        double ab = 29;
        double ba = 29;
        double aa = 14;
        double bb = 65;
        Assertions.assertEquals(ba, VectorUtil.dotProduct(a, b));
        Assertions.assertEquals(ab, VectorUtil.dotProduct(b, a));
        Assertions.assertEquals(aa, VectorUtil.dotProduct(a, a));
        Assertions.assertEquals(bb, VectorUtil.dotProduct(b, b));
        Assertions.assertEquals(0, VectorUtil.dotProduct(a, Tupel3D.ORIGIN));
        Assertions.assertEquals(0, VectorUtil.dotProduct(b, Tupel3D.ORIGIN));
    }

    @Test
    void getDistance() {
        Tupel3D point1 = new Tupel3D(3, 4, 5);
        Tupel3D point2 = new Tupel3D(-3, -4, 5);
        double distance1 = VectorUtil.getDistance(point1, point2);
        Assertions.assertEquals(10.0, distance1);
    }

    @Test
    void getDistanceXYZ() {
        Tupel3D origin = Tupel3D.ORIGIN;
        Tupel3D point1 = new Tupel3D(1, 2, 3);
        Tupel3D point2 = new Tupel3D(2, 2, 3);
        Tupel3D point3 = new Tupel3D(2, 3, 3);
        Tupel3D point4 = new Tupel3D(2, 3, 4);
        Assertions.assertEquals(VectorUtil.getDistanceX(origin, point1), VectorUtil.getDistanceX(origin, point2));
        Assertions.assertEquals(VectorUtil.getDistanceY(origin, point2), VectorUtil.getDistanceY(origin, point3));
        Assertions.assertEquals(VectorUtil.getDistanceZ(origin, point3), VectorUtil.getDistanceZ(origin, point4));
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
        Assertions.assertEquals(10, VectorUtil.getFacePointDistance(x, y, z, a));
        Assertions.assertEquals(0, VectorUtil.getFacePointDistance(x, y, z, b));
        Assertions.assertEquals(0, VectorUtil.getFacePointDistance(x, y, z, c));
        Assertions.assertEquals(-4, VectorUtil.getFacePointDistance(x, y, z, d));
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
        Assertions.assertEquals(VectorUtil.getNearestPointX(origin, points).get(), point1);
        Assertions.assertEquals(VectorUtil.getNearestPointY(origin, points).get(), point2);
        Assertions.assertEquals(VectorUtil.getNearestPointZ(origin, points).get(), point3);
        Assertions.assertEquals(VectorUtil.getNearestPoint(origin, points).get(), point4);
    }

    @Test
    void getOriginDistance() {
        Tupel3D point1 = new Tupel3D(2, 4, 4);
        double distance1 = VectorUtil.getOriginDistance(point1);
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
        Assertions.assertEquals(ex, VectorUtil.normVector(x));
        Assertions.assertEquals(ey, VectorUtil.normVector(y));
        Assertions.assertEquals(ez, VectorUtil.normVector(z));
        Assertions.assertThrows(IllegalArgumentException.class, () -> VectorUtil.normVector(Tupel3D.ORIGIN),
                "vector has length 0!");
    }

    @Test
    void vectorAddition() {
        Tupel3D a = new Tupel3D(1, 2, 3);
        Tupel3D b = new Tupel3D(0, 4, 7);
        Tupel3D ab = new Tupel3D(1, 6, 10);
        Tupel3D aa = new Tupel3D(2, 4, 6);
        Tupel3D bb = new Tupel3D(0, 8, 14);
        Assertions.assertEquals(ab, VectorUtil.vectorAddition(a, b));
        Assertions.assertEquals(ab, VectorUtil.vectorAddition(b, a));
        Assertions.assertEquals(aa, VectorUtil.vectorAddition(a, a));
        Assertions.assertEquals(bb, VectorUtil.vectorAddition(b, b));
    }

    @Test
    void vectorSubtraction() {
        Tupel3D a = new Tupel3D(1, 2, 3);
        Tupel3D b = new Tupel3D(0, 4, 7);
        Tupel3D ab = new Tupel3D(-1, 2, 4);
        Tupel3D ba = new Tupel3D(1, -2, -4);
        Tupel3D aa = new Tupel3D(0, 0, 0);
        Tupel3D bb = new Tupel3D(0, 0, 0);
        Assertions.assertEquals(ba, VectorUtil.vectorSubtraction(a, b));
        Assertions.assertEquals(ab, VectorUtil.vectorSubtraction(b, a));
        Assertions.assertEquals(aa, VectorUtil.vectorSubtraction(a, a));
        Assertions.assertEquals(bb, VectorUtil.vectorSubtraction(b, b));
    }

    @Test
    void vectorTimesScalar() {
        Tupel3D v = new Tupel3D(1, 2, 3);
        Tupel3D b = new Tupel3D(5, 10, 15);
        double x = 5;
        double y = 1;
        double z = 0;
        Assertions.assertEquals(b, VectorUtil.vectorTimesScalar(v, x));
        Assertions.assertEquals(v, VectorUtil.vectorTimesScalar(v, y));
        Assertions.assertEquals(Tupel3D.ORIGIN, VectorUtil.vectorTimesScalar(v, z));
        Assertions.assertEquals(Tupel3D.ORIGIN, VectorUtil.vectorTimesScalar(Tupel3D.ORIGIN, x));
        Assertions.assertEquals(Tupel3D.ORIGIN, VectorUtil.vectorTimesScalar(Tupel3D.ORIGIN, z));
    }
}