package com.pioneers.designpatterns.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
@RequestMapping("animalService")
public class AnimalController {

    private final AnimalProcessor2 animalProcessor;

    @GetMapping("feed/{animalName}")
    public String feedAnimalApi(@PathVariable String animalName) {

        try {
            final AnimalType animal = AnimalType.toAnimal(animalName);

            animalProcessor.feedAnimal(animal);

        } catch (AnimalType.AnimalException e) {
            return e.getMessage();
        }

        return "Successfully feed animal";
    }

    @GetMapping("makeSound/{animalName}")
    public String makeSoundApi(@PathVariable String animalName) {

        try {
            final AnimalType animal = AnimalType.toAnimal(animalName);

            animalProcessor.makeSound(animal);
        } catch (AnimalType.AnimalException e) {
            return e.getMessage();
        }

        return "Successfully made animal sound";
    }
}
