package com.pioneers.designpatterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Primary
@Component
public class Dog implements AnimalStrategy {
    public Dog() {
        log.debug("Dog bean created");
    }

    @Override
    public void feed() {
        log.info("🦴🦴🦴🦴🦴🦴🦴🦴🦴🦴🦴🦴");
    }

    @Override
    public void makeSound() {
        log.info("🦮🦮🦮🦮🦮🦮🦮🦮🦮🦮🦮🦮");
    }
}
