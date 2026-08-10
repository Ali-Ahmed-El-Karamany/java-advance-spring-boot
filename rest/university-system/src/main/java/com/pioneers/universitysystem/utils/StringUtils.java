package com.pioneers.universitysystem.utils;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean isNullOrBlank(final String string) {
        return string == null || string.isBlank();
    }
}
