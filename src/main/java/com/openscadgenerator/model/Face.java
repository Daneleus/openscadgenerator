package com.openscadgenerator.model;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public record Face(List<Integer> pointOrder) {

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "[%s]",
                pointOrder.stream().map(l -> l + "").collect(Collectors.joining(",")));
    }
}
