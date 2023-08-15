package com.openscadgenerator.service;

import java.util.List;

import com.openscadgenerator.geometry.Tupel3D;

public class Tupel3DService {

    public static final int OFFSET = 10;

    public static Tupel3D maxDiameterVector(List<Tupel3D> points) {
        double diameterX =
                points.stream().map(Tupel3D::x).max(Double::compareTo).orElse(0.0) - points.stream().map(Tupel3D::x)
                        .min(Double::compareTo).orElse(0.0);
        double diameterY =
                points.stream().map(Tupel3D::y).max(Double::compareTo).orElse(0.0) - points.stream().map(Tupel3D::y)
                        .min(Double::compareTo).orElse(0.0);
        double diameterZ =
                points.stream().map(Tupel3D::z).max(Double::compareTo).orElse(0.0) - points.stream().map(Tupel3D::z)
                        .min(Double::compareTo).orElse(0.0);
        return new Tupel3D(Math.abs(diameterX) + OFFSET, Math.abs(diameterY) + OFFSET, Math.abs(diameterZ) + OFFSET);
    }

}
