package com.pioneers.designpatterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Lion implements AnimalStrategy {
    public Lion() {
        log.debug("Lion bean created");
    }

    @Override
    public void feed() {
        log.info("🍗🍗🍗🍗🍗🍗🍗🍗🍗🍗🍗🍗");
    }

    @Override
    public void makeSound() {
        log.info("🦁🦁🦁🦁🦁🦁🦁🦁🦁🦁🦁🦁");
    }
}
