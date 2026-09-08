package com.pioneers.designpatterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnimalProcessor {
    private final AnimalStrategy strategy;

    @Autowired
    public AnimalProcessor(@Qualifier("tiger") AnimalStrategy Strategy) {
        this.strategy = Strategy;
        log.debug("AnimalProcessor bean Created [{}]", strategy.getClass().getSimpleName());
    }

    public void feedAnimal() {
        strategy.feed();
    }

    public void makeSound() {
        strategy.makeSound();
    }
}
