package com.pioneers.designpatterns.strategy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
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
                .orElseThrow(() -> new AnimalException(animalType + " is not listed in our system"));
    }

    public static class AnimalException extends RuntimeException {
        public AnimalException(String message) {
            super(message);
        }
    }
}
