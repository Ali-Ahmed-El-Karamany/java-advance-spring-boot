package com.pioneers.universitysystem.utils;

public class NameBuilderUtils {
    private NameBuilderUtils() {

    }

    public static String buildFullName(final String firstName, final String lastName) {
        return firstName + lastName;
    }
}
