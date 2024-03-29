package com.github.designpatterns;

public class Client {
    public static void main(String[] args) {
        // Create leaf shapes
        Shape circle = new Circle();
        Shape square = new Square();

        // Create composite shape
        Drawing drawing = new Drawing();
        drawing.addShape(circle);
        drawing.addShape(square);

        // Draw composite shape
        drawing.draw();
    }
}
