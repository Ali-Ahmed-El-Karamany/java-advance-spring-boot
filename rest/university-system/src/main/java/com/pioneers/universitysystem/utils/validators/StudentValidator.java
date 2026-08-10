package com.pioneers.universitysystem.utils.validators;

import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;

import java.util.ArrayList;
import java.util.List;

import static com.pioneers.universitysystem.utils.StringUtils.isNullOrBlank;

public class StudentValidator {
    private StudentValidator() {
    }

    public static List<String> validateRegisterRequest(
            final StudentRegisterRequest request
    ) {
        final List<String> errorList = new ArrayList<>();

        if (isNullOrBlank(request.getFirstName())) {
            errorList.add("First name is empty");
        }

        if (isNullOrBlank(request.getLastName())) {
            errorList.add("Last name is empty");
        }

        if (!isEligible(request.getAge())) {
            errorList.add("Age is out of range");
        }

        if (isInvalidEmail(request.getEmail())) {
            errorList.add("Email is Invalid");
        }

        if (isPasswordInvalid(request.getPassword())) {
            errorList.add("Password is Invalid");
        }

        return errorList;
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
