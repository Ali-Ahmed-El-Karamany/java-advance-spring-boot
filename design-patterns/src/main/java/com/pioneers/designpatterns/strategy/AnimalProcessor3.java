package com.pioneers.designpatterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AnimalProcessor3 {
    private final Map<AnimalType, AnimalStrategy> strategies;

    @Autowired
    public AnimalProcessor3(Set<AnimalStrategy> strategies) {
        this.strategies = Arrays.stream(AnimalType.values())
                .collect(Collectors.toUnmodifiableMap(
                        animal -> animal,
                        animal -> getFirstStrategy(strategies, animal)
                                .orElseThrow(
                                        () -> new IllegalStateException(
                                                "No AnimalService found for animal type: " + animal
                                        ))
                ));
    }

    public void feedAnimal(AnimalType animal) throws AnimalType.AnimalException {
        AnimalProcessor3.getAnimalStrategy(strategies, animal)
                .ifPresent(AnimalStrategy::feed);
    }

    public void makeSound(AnimalType animal) {
        AnimalProcessor3.getAnimalStrategy(strategies, animal)
                .ifPresent(AnimalStrategy::makeSound);
    }

    private static Optional<AnimalStrategy> getAnimalStrategy(
            final Map<AnimalType, AnimalStrategy> animalStrategies,
            final AnimalType animalType
    ) {
        return Optional.ofNullable(animalStrategies.get(animalType));
    }

    private static Optional<AnimalStrategy> getFirstStrategy(
            final Set<AnimalStrategy> strategies,
            final AnimalType animal
    ) {
        return strategies
                .stream()
                .filter(strategy -> strategy.isTypeAligned(animal))
                .findFirst();
    }
}
