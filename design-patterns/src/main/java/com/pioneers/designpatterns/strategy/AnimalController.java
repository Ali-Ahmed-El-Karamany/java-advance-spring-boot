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

    private final AnimalProcessor animalProcessor;

    @GetMapping("feed/{animalName}")
    public String feedAnimalApi(@PathVariable String animalName) {

        animalProcessor.feedAnimal();

        return "Successfully feed animal";
    }

    @GetMapping("makeSound/{animalName}")
    public String makeSoundApi(@PathVariable String animalName) {
        animalProcessor.makeSound();
        return "Successfully made animal sound";
    }
}
