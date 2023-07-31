package com.openscadgenerator.geometry;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public record Face(List<Integer> pointOrder) {

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Face)) {
            return false;
        }
        return new HashSet<>(this.pointOrder()).containsAll(((Face)obj).pointOrder()) && new HashSet<>(
                ((Face)obj).pointOrder()).containsAll(this.pointOrder());
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "[%s]",
                pointOrder.stream().map(l -> l + "").collect(Collectors.joining(",")));
    }
}
