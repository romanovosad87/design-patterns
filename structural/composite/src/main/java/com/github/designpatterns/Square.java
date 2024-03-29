package com.github.designpatterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing square ...");
    }
}
