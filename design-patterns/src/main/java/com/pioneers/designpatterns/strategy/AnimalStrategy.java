package com.pioneers.designpatterns.strategy;

public interface AnimalStrategy {
    boolean isTypeAligned(AnimalType animal);

    void feed();

    void makeSound();
}
