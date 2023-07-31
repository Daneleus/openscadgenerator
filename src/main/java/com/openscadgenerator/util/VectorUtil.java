package com.openscadgenerator.util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.openscadgenerator.geometry.Tupel3D;

public class VectorUtil {
    static Tupel3D crossProduct(Tupel3D a, Tupel3D b) {
        return new Tupel3D(
                a.y() * b.z() - a.z() * b.y(),
                a.z() * b.x() - a.x() * b.z(),
                a.x() * b.y() - a.y() * b.x()
        );
    }

    static double dotProduct(Tupel3D a, Tupel3D b) {
        return a.x() * b.x() + a.y() * b.y() + a.z() * b.z();
    }

    static double getFacePointDistance(Tupel3D facePointA, Tupel3D facePointB, Tupel3D facePointC,
            Tupel3D point) {
        return getHesseNormalForm(facePointA, facePointB, facePointC).apply(point);
    }

    private static Function<Tupel3D, Double> getHesseNormalForm(Tupel3D a, Tupel3D b, Tupel3D c) {
        Tupel3D norm = crossProduct(getVector(a, b), getVector(a, c));
        double normLength = getDistance(norm, Tupel3D.ORIGIN);
        Tupel3D norm1 = new Tupel3D(norm.x() / normLength, norm.y() / normLength, norm.z() / normLength);
        return point -> dotProduct(norm1, vectorSubtraction(point, a));
    }

    public static Optional<Tupel3D> getNearestPoint(Tupel3D point3D, List<Tupel3D> point3DList) {
        return point3DList.stream().min(Comparator.comparingDouble(point -> getDistance(point3D, point)));
    }

    public static double getDistance(Tupel3D point1, Tupel3D point2) {
        double diffX = point1.x() - point2.x();
        double diffY = point1.y() - point2.y();
        double diffZ = point1.z() - point2.z();
        return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
    }

    public static Optional<Tupel3D> getNearestPointX(Tupel3D point3D, List<Tupel3D> point3DList) {
        return point3DList.stream().min(Comparator.comparingDouble(point -> getDistanceX(point3D, point)));
    }

    public static double getDistanceX(Tupel3D point1, Tupel3D point2) {
        double diffY = point1.y() - point2.y();
        double diffZ = point1.z() - point2.z();
        return Math.sqrt(diffY * diffY + diffZ * diffZ);
    }

    public static Optional<Tupel3D> getNearestPointY(Tupel3D point3D, List<Tupel3D> point3DList) {
        return point3DList.stream().min(Comparator.comparingDouble(point -> getDistanceY(point3D, point)));
    }

    public static double getDistanceY(Tupel3D point1, Tupel3D point2) {
        double diffX = point1.x() - point2.x();
        double diffZ = point1.z() - point2.z();
        return Math.sqrt(diffX * diffX + diffZ * diffZ);
    }

    public static Optional<Tupel3D> getNearestPointZ(Tupel3D point3D, List<Tupel3D> point3DList) {
        return point3DList.stream().min(Comparator.comparingDouble(point -> getDistanceZ(point3D, point)));
    }

    public static double getDistanceZ(Tupel3D point1, Tupel3D point2) {
        double diffX = point1.x() - point2.x();
        double diffY = point1.y() - point2.y();
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    static Tupel3D getVector(Tupel3D a, Tupel3D b) {
        return vectorSubtraction(b, a);
    }

    static Tupel3D normVector(Tupel3D v) {
        if (v.isOrigin()) {
            throw new IllegalArgumentException("vector has length 0!");
        }
        return vectorTimesScalar(v, 1 / getOriginDistance(v));
    }

    static Tupel3D vectorTimesScalar(Tupel3D a, double b) {
        return new Tupel3D(
                a.x() * b,
                a.y() * b,
                a.z() * b
        );
    }

    public static double getOriginDistance(Tupel3D point) {
        return getDistance(point, Tupel3D.ORIGIN);
    }

    static Tupel3D vectorAddition(Tupel3D a, Tupel3D b) {
        return new Tupel3D(
                a.x() + b.x(),
                a.y() + b.y(),
                a.z() + b.z()
        );
    }

    static Tupel3D vectorSubtraction(Tupel3D a, Tupel3D b) {
        return vectorAddition(a, vectorTimesScalar(b, -1));
    }
}
