package com.pioneers.designpatterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Lion implements AnimalStrategy {
    private static final AnimalType LION = AnimalType.LION;

    public Lion() {
        log.debug("Lion bean created");
    }

    @Override
    public boolean isTypeAligned(AnimalType animal) {
        return LION.isAnimalType(animal);
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
