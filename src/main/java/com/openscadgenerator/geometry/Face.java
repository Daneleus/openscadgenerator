package com.openscadgenerator.geometry;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public record Face(List<Integer> pointOrder) {

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Face face)) {
            return false;
        }
        return new HashSet<>(pointOrder()).containsAll(face.pointOrder()) && new HashSet<>(
                face.pointOrder()).containsAll(pointOrder());
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "[%s]",
                pointOrder.stream().map(l -> l + "").collect(Collectors.joining(",")));
    }
}
