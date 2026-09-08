package com.pioneers.designpatterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Dog implements AnimalStrategy {
    private static final AnimalType DOG = AnimalType.DOG;

    public Dog() {
        log.debug("Dog bean created");
    }

    @Override
    public boolean isTypeAligned(AnimalType animal) {
        return DOG.isAnimalType(animal);
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
