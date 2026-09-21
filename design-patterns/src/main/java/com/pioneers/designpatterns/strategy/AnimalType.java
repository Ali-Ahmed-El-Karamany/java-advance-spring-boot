package com.pioneers.designpatterns.strategy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Getter
@Slf4j
@RequiredArgsConstructor
public enum AnimalType {
    LION("lion"),
    DOG("dog"),
    TIGER("tiger");

    private final String animalType;

    public static AnimalType toAnimal(final String animalType) throws AnimalException {
        return Arrays.stream(AnimalType.values())
                .filter(animal -> animal.getAnimalType().equalsIgnoreCase(animalType))
                .findFirst()
                .orElseThrow(() -> {
                        log.debug("{} is not listed in the system", animalType);
                        return new AnimalException(animalType + " is not listed in our system");
                });
    }

    public boolean isAnimalType(final AnimalType animalType) {
        return this.getAnimalType().equalsIgnoreCase(animalType.getAnimalType());
    }

    public static class AnimalException extends RuntimeException {
        public AnimalException(String message) {
            super(message);
        }
    }
}
