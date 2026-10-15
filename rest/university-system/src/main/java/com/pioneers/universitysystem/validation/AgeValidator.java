package com.pioneers.universitysystem.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgeValidator implements ConstraintValidator<Age, Integer> {

    private byte min;
    private byte max;

    @Override
    public void initialize(Age annotation) {
        this.min = annotation.min();
        this.max = annotation.max();

        log.debug("initialize(), initialized age min value with [{}] and max value with [{}] ", min, max);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        log.debug("isValid(), validating input values of @Age");

        if (value == null) {
            return false;
        }

        return value >= min && value <= max;
    }
}
