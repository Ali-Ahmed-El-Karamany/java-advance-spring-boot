package com.pioneers.universitysystem.utils.validators;

import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;

import java.util.HashMap;
import java.util.Map;

import static com.pioneers.universitysystem.utils.StringUtils.isNullOrBlank;

public final class StudentValidator {

    private StudentValidator() {
        throw new AssertionError("Utility Class");
    }

    public static Map<String, String> validateRegisterRequest(
            final StudentRegisterRequest request
    ) {
        final Map<String, String> errors = new HashMap<>();

        if (isNullOrBlank(request.firstName())) {
            errors.put("firstName", "First name is required");
        }

        if (isNullOrBlank(request.lastName())) {
            errors.put("lastName", "Last name is required");
        }

        if (!isEligible(request.age())) {
            errors.put("age", "Age is out of range");
        }

        if (isInvalidEmail(request.email())) {
            errors.put("email", "must be a valid email address");
        }

        if (isPasswordInvalid(request.password())) {
            errors.put("password", "Invalid Password");
        }

        return errors;
    }

    private static boolean isEligible(final int age) {
        return age >= 18 && age <= 25;
    }

    private static boolean isInvalidEmail(final String email) {
        return isNullOrBlank(email) || !email.contains("@");
    }

    private static boolean isPasswordInvalid(final String password) {
        return isNullOrBlank(password) || password.length() < 8 || password.length() > 32;
    }
}
