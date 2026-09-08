package com.pioneers.designpatterns.factory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ShapeType {
    Circle("circle"),
    Square("square"),
    Triangle("triangle");

    private final String shapeName;

    public static ShapeType toShape(final String shapeName) throws IllegalArgumentException {
        return Arrays.stream(ShapeType.values())
                .filter(shapeType -> shapeType.getShapeName().equalsIgnoreCase(shapeName))
                .findFirst()
                .orElseThrow(() -> new ShapeException("Invalid Shape Type"));
    }

    public static class ShapeException extends RuntimeException {
        public ShapeException(String message) {
            super(message);
        }
    }
}
