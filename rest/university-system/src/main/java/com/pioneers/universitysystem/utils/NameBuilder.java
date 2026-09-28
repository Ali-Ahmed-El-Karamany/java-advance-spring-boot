package com.pioneers.universitysystem.utils;

public final  class NameBuilder {

    private NameBuilder() {
        throw new AssertionError("Utility Class");
    }

    public static String buildFullName(final String firstName, final String lastName) {
        return firstName + " " + lastName;
    }
}
