package com.openscadgenerator.model;

public interface Validatable {

    public static final Validatable INVALID = new Validatable() {
        @Override public Validatable validate() {
            return this;
        }
    };

    public default Validatable validate() {
        return INVALID;
    }
}
