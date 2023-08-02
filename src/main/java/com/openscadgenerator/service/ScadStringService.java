package com.openscadgenerator.service;

import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.scad.ScadString;

public class ScadStringService {

    public static ScadString difference(ScadString minuent, ScadString subtrahent) {
        return new ScadString("difference(){" + minuent.content() + "" + subtrahent.content() + "}");
    }

    public static ScadString intersection(ScadString scadString1, ScadString scadString2) {
        return new ScadString("intersection(){" + scadString1.content() + "" + scadString2.content() + "}");
    }

    public static ScadString moveToPosition(Tupel3D point3D, ScadString scadString) {
        return new ScadString("translate(" + point3D + "){" + scadString.content() + "}");
    }

    public static ScadString rotate(ScadString scadString, Tupel3D rotateAxisVector, double angle) {

        return new ScadString("rotate(a="
                              + angle
                              + ", v=["
                              + rotateAxisVector.x()
                              + ","
                              + rotateAxisVector.y()
                              + ","
                              + rotateAxisVector.z()
                              + "]){"
                              + scadString.content()
                              + "}");
    }

    public static ScadString union(ScadString scadString1, ScadString scadString2) {
        return new ScadString("union(){" + scadString1.content() + "" + scadString2.content() + "}");
    }

}
