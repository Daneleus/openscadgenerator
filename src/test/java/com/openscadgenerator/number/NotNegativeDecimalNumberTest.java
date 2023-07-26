package com.openscadgenerator.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotNegativeDecimalNumberTest {

    @Test
    void validate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new NotNegativeDecimalNumber(-1),
                "value must be not negative!");
    }
}