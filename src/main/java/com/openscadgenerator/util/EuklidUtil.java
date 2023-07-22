package com.openscadgenerator.util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.openscadgenerator.model.Point3D;

public class EuklidUtil {

    public static Optional<Point3D> getNearestPoint(Point3D point3D, List<Point3D> point3DList) {
        return point3DList.stream().min(Comparator.comparingDouble(point -> getDistance(point3D, point)));
    }

    public static double getDistance(Point3D point1, Point3D point2) {
        double diffX = point1.getX() - point2.getX();
        double diffY = point1.getY() - point2.getY();
        double diffZ = point1.getZ() - point2.getZ();
        return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
    }

    public static double getOriginDistance(Point3D point) {
        return getDistance(point, Point3D.ORIGIN);
    }
}
