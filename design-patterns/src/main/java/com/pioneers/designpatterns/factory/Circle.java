package com.pioneers.designpatterns.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Circle implements Shape {

    @Override
    public void draw() {
        log.info("Drawing Circle");
    }
}
