package com.openscadgenerator.shapes;

import java.util.Collections;
import java.util.Locale;

import com.openscadgenerator.model.Diameter;
import com.openscadgenerator.model.Fragments;
import com.openscadgenerator.model.Height;
import com.openscadgenerator.model.ScadString;
import com.openscadgenerator.model.Shape;
import com.openscadgenerator.util.ScadUtil;

@SuppressWarnings("unused")
public class Cone extends Shape<Cone> {

    private Diameter diameterBottom = Diameter.DEFAULT;

    private Diameter diameterTop = Diameter.DEFAULT;

    private Fragments fragments = Fragments.DEFAULT;

    private Height height = Height.DEFAULT;

    private Diameter innerDiameterBottom = new Diameter().value(0);

    private Diameter innerDiameterTop = new Diameter().value(0);

    private Fragments innerFragments = Fragments.DEFAULT;

    public Cone diameter(Diameter diameter) {
        this.diameterBottom = diameter;
        this.diameterTop = diameter;
        return this;
    }

    public Cone diameterBottom(Diameter diameter) {
        this.diameterBottom = diameter;
        return this;
    }

    public Cone diameterTop(Diameter diameter) {
        this.diameterTop = diameter;
        return this;
    }

    public Cone fragments(Fragments fragments) {
        this.fragments = fragments;
        return this;
    }

    @Override
    public ScadString generate() {

        ScadString scadString;

        if (isConeTube()) {
            scadString = generateConeTube(getHeight().getValue(), getDiameterBottom().getValue(),
                    getDiameterTop().getValue(), getFragments().getValue(), getInnerDiameterBottom().getValue(),
                    getInnerDiameterTop().getValue(), getInnerFragments().getValue());
        }
        else if (isCone()) {
            scadString =
                    generateCone(getHeight().getValue(), getDiameterBottom().getValue(), getDiameterTop().getValue(),
                            getFragments().getValue());
        }
        else if (isCylinderTube()) {
            scadString = generateCylinderTube(getHeight().getValue(), getDiameterBottom().getValue(),
                    getFragments().getValue(), getInnerDiameterBottom().getValue(), getInnerFragments().getValue());
        }
        else {
            scadString =
                    generateCylinder(getHeight().getValue(), getDiameterBottom().getValue(), getFragments().getValue());
        }

        if (getPosition().isOrigin()) {
            return scadString;
        }
        else {
            return ScadUtil.moveToPosition(getPosition(), scadString);
        }
    }

    private ScadString generateConeTube(double height, double diameterBottom, double diameterTop, long fragments,
            double innerDiameterBottom, double innerDiameterTop, long innerFragments) {
        return ScadUtil.differenceAll(generateCone(height, diameterBottom, diameterTop, fragments),
                Collections.singletonList(generateCone(height, innerDiameterBottom, innerDiameterTop, innerFragments)));
    }

    public Diameter getDiameterBottom() {
        return diameterBottom;
    }

    public Diameter getDiameterTop() {
        return diameterTop;
    }

    public Height getHeight() {
        return height;
    }

    public Diameter getInnerDiameterBottom() {
        return innerDiameterBottom;
    }

    public Diameter getInnerDiameterTop() {
        return innerDiameterTop;
    }

    public Fragments getFragments() {
        return fragments;
    }

    public Fragments getInnerFragments() {
        return innerFragments;
    }

    private ScadString generateCone(double height, double diameterBottom, double diameterTop, long fragments) {
        return new ScadString(
                String.format(Locale.ENGLISH, "cylinder(h=%.4f,d1=%.4f,d2=%.4f,$fn=%d);", height, diameterBottom,
                        diameterTop, fragments));
    }

    private ScadString generateCylinderTube(double height, double diameter, long fragments, double innerDiameterBottom,
            long innerFragments) {
        return ScadUtil.differenceAll(generateCylinder(height, diameter, fragments),
                Collections.singletonList(generateCylinder(height, innerDiameterBottom, innerFragments)));
    }

    private ScadString generateCylinder(double height, double diameter, long fragments) {
        return new ScadString(
                String.format(Locale.ENGLISH, "cylinder(h=%.4f,d=%.4f,$fn=%d);", height, diameter, fragments));
    }

    @Override public boolean isInvalid() {
        return getDiameterBottom().getValue() <= 0
               && getDiameterTop().getValue() <= 0
               || getHeight().getValue() <= 0
               || getInnerDiameterBottom().getValue() < 0
               || getInnerDiameterTop().getValue() < 0
               || getFragments().getValue() < 3
               || getInnerFragments().getValue() < 3
               || getDiameterBottom().getValue() < getInnerDiameterBottom().getValue()
               || getDiameterTop().getValue() < getInnerDiameterTop().getValue();
    }

    public Cone height(Height height) {
        this.height = height;
        return this;
    }

    public Cone innerDiameter(Diameter diameter) {
        this.innerDiameterBottom = diameter;
        this.innerDiameterTop = diameter;
        return this;
    }

    public Cone innerDiameterBottom(Diameter diameter) {
        this.innerDiameterBottom = diameter;
        return this;
    }

    public Cone innerDiameterTop(Diameter diameter) {
        this.innerDiameterTop = diameter;
        return this;
    }

    public Cone innerFragments(Fragments fragments) {
        this.innerFragments = fragments;
        return this;
    }

    public boolean isCone() {
        return getDiameterBottom() != getDiameterTop();
    }

    public boolean isConeTube() {
        return isCone() && isTubeCylindric() || isTubeConic();
    }

    public boolean isCylinder() {
        return getDiameterBottom() == getDiameterTop();
    }

    public boolean isCylinderTube() {
        return isCylinder() && isTubeCylindric();
    }

    public boolean isTubeConic() {
        Diameter diameter1 = getInnerDiameterBottom();
        if (!(diameter1.getValue() != 0))
            return false;
        Diameter diameter = getInnerDiameterTop();
        return diameter.getValue() != 0
               && getInnerDiameterBottom() != getInnerDiameterTop();
    }

    public boolean isTubeCylindric() {
        Diameter diameter1 = getInnerDiameterBottom();
        if (!(diameter1.getValue() != 0))
            return false;
        Diameter diameter = getInnerDiameterTop();
        return diameter.getValue() != 0
               && getInnerDiameterBottom() == getInnerDiameterTop();
    }

}
