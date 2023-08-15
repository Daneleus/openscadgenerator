package com.openscadgenerator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.openscadgenerator.geometry.Face;
import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.shape.Polyhedron;

public class PolyhedronService {

    public static Polyhedron autoFacing(Polyhedron polyhedron) {
        return polyhedron.faces(autoFacing(polyhedron.getPoints()));
    }

    public static List<Face> autoFacing(List<Tupel3D> points) {
        List<Face> autoFaces = new ArrayList<>();
        int size = points.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    int countPositive = 0;
                    int countNegative = 0;
                    for (int l = 0; l < size; l++) {
                        if (l == i || l == j || l == k) {
                            continue;
                        }
                        double signum = Math.signum(
                                VectorService.getFacePointDistance(points.get(i), points.get(j), points.get(k),
                                        points.get(l)) + 100 - 100); //Underflow-Correction
                        if (signum > 0) {
                            countPositive++;
                        }
                        else if (signum < 0) {
                            countNegative++;
                        }
                    }
                    if (countPositive == 0) {
                        autoFaces.add(new Face(new ArrayList<>(Arrays.asList(k, i, j))));
                    }
                    if (countNegative == 0) {
                        autoFaces.add(new Face(new ArrayList<>(Arrays.asList(j, i, k))));
                    }
                }
            }
        }
        return autoFaces;
    }

}
