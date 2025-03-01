package com.openscadgenerator.shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.font.TextFont;
import com.openscadgenerator.scad.ScadString;
import com.openscadgenerator.service.FileService;

public class TextTest {

    @Test
    void defaultValues() {
        ScadString scadString = new Text().generate();
        FileService.writeScadStringToFile(scadString, "src\\test\\samples", "testDefault");
        Assertions.assertEquals("linear_extrude(100.0000){text( \"Test\" , font=Consolas, size=100.0);}", scadString.content());
    }
}
