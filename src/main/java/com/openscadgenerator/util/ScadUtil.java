package com.openscadgenerator.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.openscadgenerator.model.Point3D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.shape.Shape;

public class ScadUtil {

    public static ScadString differenceAll(ScadString minuent, List<ScadString> scadStrings) {
        return new ScadString("difference(){" + minuent.content() + "" + unionAll(scadStrings).content() + "}");
    }

    public static ScadString unionAll(List<ScadString> scadStrings) {
        if (scadStrings.size() == 0) {
            return new ScadString("");
        }
        if (scadStrings.size() == 1) {
            return scadStrings.get(0);
        }
        String[] union = { "union(){" };
        scadStrings.forEach(scad -> union[0] += scad.content());

        return new ScadString(union[0] + "}");
    }

    public static void generateScadFile(ScadString scadString, String path, String filename) {
        FileUtil.writeToFile(scadString.content(), FileUtil.getOrCreateFile(path, filename));
    }

    public static void generateScadFile(Shape<?> shape, String path, String filename) {
        generateScadFile(Collections.singletonList(shape), path, filename);
    }

    public static void generateScadFile(List<Shape<?>> shapes, String path, String filename) {
        FileUtil.writeToFile(shapes.stream().map(shape -> shape.generate().content()).collect(Collectors.joining()),
                FileUtil.getOrCreateFile(path, filename));
    }

    public static ScadString intersectionAll(List<ScadString> scadStrings) {
        String[] intersection = { "intersection(){" };
        scadStrings.forEach(scad -> intersection[0] += scad.content());

        return new ScadString(intersection[0] + "}");
    }

    public static ScadString moveToPosition(Point3D point3D, ScadString scad) {
        return new ScadString("translate(" + point3D.toString() + "){" + scad.content() + "}");
    }

    public static ScadString rotate(ScadString scadString, Point3D rotateAxis, double angle) {

        return new ScadString("rotate(a="
                              + angle
                              + ", v=["
                              + rotateAxis.getX()
                              + ","
                              + rotateAxis.getY()
                              + ","
                              + rotateAxis.getZ()
                              + "]){"
                              + scadString.content()
                              + "}");
    }

    public static ScadString rotate(ScadString scadString, Point3D angles) {

        return new ScadString("rotate(a=["
                              + angles.getX()
                              + ","
                              + angles.getY()
                              + ","
                              + angles.getZ()
                              + "]){"
                              + scadString.content()
                              + "}");
    }

}
