package com.pioneers.universitysystem.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AgeValidator.class})
public @interface Age {

    byte min() default 0;
    byte max() default Byte.MAX_VALUE;

    String message() default "Age is incompatible";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
