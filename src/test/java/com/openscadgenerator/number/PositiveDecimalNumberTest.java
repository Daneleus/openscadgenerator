package com.openscadgenerator.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PositiveDecimalNumberTest {

    @Test
    void validate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PositiveDecimalNumber(0));
    }
}