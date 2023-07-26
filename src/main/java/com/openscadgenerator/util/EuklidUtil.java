package com.openscadgenerator.util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.openscadgenerator.geometry.Tupel3D;

public class EuklidUtil {

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

    public static double getOriginDistance(Tupel3D point) {
        return getDistance(point, Tupel3D.ORIGIN);
    }
}
