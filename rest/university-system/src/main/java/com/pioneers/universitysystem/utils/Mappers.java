package com.pioneers.universitysystem.utils;

import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.models.entities.Student;

import static com.pioneers.universitysystem.utils.NameBuilder.buildFullName;

public final class Mappers {
    private Mappers() {
        throw new AssertionError("Utility class");
    }

    public static StudentResponse toStudentResponse(final Student student) {
        return new StudentResponse(student.getFullName(), student.getAge(), student.getEmail());
    }

    public static StudentResponse toStudentResponse(final StudentRegisterRequest registerRequest) {
        final String fullName = buildFullName(registerRequest.firstName(),  registerRequest.lastName());

        return new StudentResponse(fullName, registerRequest.age(), registerRequest.email());
    }
}
