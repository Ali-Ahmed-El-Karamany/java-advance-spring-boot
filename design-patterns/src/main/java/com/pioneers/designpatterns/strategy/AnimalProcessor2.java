package com.pioneers.designpatterns.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnimalProcessor2 {
    private final List<AnimalStrategy> strategies;

    public void feedAnimal(AnimalType animal) throws AnimalType.AnimalException {
        AnimalProcessor2.getFirstStrategy(strategies, animal)
                .ifPresent(AnimalStrategy::feed);
    }

    public void makeSound(AnimalType animal) {
        AnimalProcessor2.getFirstStrategy(strategies, animal)
                .ifPresent(AnimalStrategy::makeSound);
    }

    private static Optional<AnimalStrategy> getFirstStrategy(
            final List<AnimalStrategy> strategies,
            final AnimalType animal
    ) {
        return strategies
                .stream()
                .filter(strategy -> strategy.isTypeAligned(animal))
                .findFirst();
    }
}
