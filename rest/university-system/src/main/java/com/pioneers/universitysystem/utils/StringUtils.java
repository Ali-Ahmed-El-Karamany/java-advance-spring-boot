package com.pioneers.universitysystem.utils;

public  final class StringUtils {
    private StringUtils() {
        throw new AssertionError("Utility class");
    }

    public static boolean isNullOrBlank(final String string) {
        return string == null || string.isBlank();
    }
}
