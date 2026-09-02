package com.pioneers.designpatterns.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShapeFactory {

    public static Shape getShape(ShapeType type) {
        if (ShapeType.Circle.equals(type)) {
            return new Circle();
        }

        if (ShapeType.Square.equals(type)) {
            return new Square();
        }

        return new Triangle();
    }
}
