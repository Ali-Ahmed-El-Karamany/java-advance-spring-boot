package com.pioneers.designpatterns.factory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shapeService")
public class ShapeController {

    @GetMapping("{shapeName}")
    public String drawShapeApi(@PathVariable String shapeName) {
        try {
            final ShapeType type = ShapeType.toShape(shapeName);
            final Shape shape = ShapeFactory.getShape(type);
            shape.draw();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "Shape Successfully initialized";
    }
}
