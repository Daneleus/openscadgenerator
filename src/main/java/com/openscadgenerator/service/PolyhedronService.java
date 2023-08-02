package com.openscadgenerator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.openscadgenerator.geometry.Face;
import com.openscadgenerator.geometry.Tupel3D;

public class PolyhedronService {

    public static List<Face> autoFacing(List<Tupel3D> points) {
        List<Face> autoFaces = new ArrayList<>();
        int size = points.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    int countPositive = 0;
                    int countNegative = 0;
                    List<Integer> facePointsCandidate = new ArrayList<>(Arrays.asList(i, j, k));
                    for (int l = 0; l < size; l++) {
                        if (l == i || l == j || l == k) {
                            continue;
                        }
                        double signum = Math.signum(
                                VectorService.getFacePointDistance(points.get(i), points.get(j), points.get(k),
                                        points.get(l)));
                        if (signum > 0) {
                            countPositive++;
                        }
                        else if (signum < 0) {
                            countNegative++;
                        }
                    }
                    if (countPositive == 0 || countNegative == 0) {
                        autoFaces.add(new Face(facePointsCandidate));
                    }
                }
            }
        }
        return autoFaces;
    }

}
