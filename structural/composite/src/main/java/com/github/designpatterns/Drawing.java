package com.github.designpatterns;

import java.util.ArrayList;
import java.util.List;

public class Drawing implements Shape {
    private List<Shape> shapes;

    public Drawing() {
        this.shapes = new ArrayList<>();
    }

    @Override
    public void draw() {
        shapes.forEach(Shape::draw);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }
}
