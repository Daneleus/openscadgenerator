package com.openscadgenerator.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Greater2IntegerNumberTest {

    @Test
    void validate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Greater2IntegerNumber(0));
    }
}