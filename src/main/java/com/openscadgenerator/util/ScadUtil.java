package com.openscadgenerator.util;

import java.util.List;
import java.util.stream.Collectors;

import com.openscadgenerator.model.Point3D;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;

public class ScadUtil {

    public static ScadString difference(ScadString minuent, ScadString subtrahent) {
        return new ScadString("difference(){" + minuent.content() + "" + subtrahent.content() + "}");
    }

    public static void generateScad(List<Shape<?>> shapes, String path, String filename) {
        FileUtil.writeToFile(shapes.stream().map(shape -> shape.generate().content()).collect(Collectors.joining()),
                FileUtil.getOrCreateFile(path, filename));
    }

    public static ScadString moveToPosition(Point3D point3D, ScadString scad) {
        return new ScadString("translate(" + point3D.toString() + "){" + scad.content() + "}");
    }
}
