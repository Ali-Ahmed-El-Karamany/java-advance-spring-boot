package com.pioneers.designpatterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Tiger implements AnimalStrategy {
    private static final AnimalType TIGER = AnimalType.TIGER;

    public Tiger() {
        log.debug("Tiger bean created");
    }

    @Override
    public boolean isTypeAligned(AnimalType animal) {
        return TIGER.isAnimalType(animal);
    }

    @Override
    public void feed() {
        log.info("🐷🐷🐷🐷🐷🐷🐷🐷🐷🐷🐷🐷");
    }

    @Override
    public void makeSound() {
        log.info("🐅🐅🐅🐅🐅🐅🐅🐅🐅🐅🐅🐅");
    }
}
