package com.openscadgenerator.shape;

import java.util.Locale;

import com.openscadgenerator.font.TextFont;
import com.openscadgenerator.geometry.Tupel3D;
import com.openscadgenerator.number.DecimalNumber;
import com.openscadgenerator.number.PositiveDecimalNumber;
import com.openscadgenerator.scad.ScadString;

public class Text extends Shape {
    private TextFont font = TextFont.Consolas;

    private DecimalNumber height = PositiveDecimalNumber.DEFAULT;

    private PositiveDecimalNumber size = PositiveDecimalNumber.DEFAULT;

    private String text = "Test";

    public void font(TextFont font) {
        this.font = font;
    }

    @Override protected ScadString generateShape() {
        return new ScadString( String.format(Locale.ENGLISH, "linear_extrude(%.4f){text( \"" + getText() + "\" , font=" + getFont() + ", size=" + getSize().value() + ");}", getHeight().value()));
    }

    @Override public boolean isInvalid() {
        return false;
    }

    @Override protected Tupel3D getCenter() {
        return Tupel3D.ORIGIN; //FIXME
    }

    public String getText() {
        return text;
    }

    public TextFont getFont() {
        return font;
    }

    public PositiveDecimalNumber getSize() {
        return size;
    }

    public DecimalNumber getHeight() {
        return height;
    }

    public void height(DecimalNumber height) {
        this.height = height;
    }

    public void size(PositiveDecimalNumber size) {
        this.size = size;
    }

    public void text(String text) {
        this.text = text;
    }
}
