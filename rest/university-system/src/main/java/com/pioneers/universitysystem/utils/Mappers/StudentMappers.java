package com.pioneers.universitysystem.utils.Mappers;

import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;
import com.pioneers.universitysystem.models.dtos.responses.AddressResponse;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.models.entities.Student;

import java.util.Optional;

import static com.pioneers.universitysystem.utils.Mappers.AddressMapper.toAddressResponse;
import static com.pioneers.universitysystem.utils.NameBuilder.buildFullName;

public final class StudentMappers {
    private StudentMappers() {
        throw new AssertionError("Utility class");
    }

    public static StudentResponse toStudentResponse(final Student student) {
        final Optional<AddressResponse> optionalAddressResponse = toAddressResponse(student.getAddress());

        return new StudentResponse(
                student.getFullName(),
                student.getAge(),
                student.getEmail(),
                student.getScore(),
                optionalAddressResponse);
    }

    public static StudentResponse toStudentResponse(final StudentRegisterRequest registerRequest) {
        final String fullName = buildFullName(registerRequest.firstName(), registerRequest.lastName());

        final Optional<AddressResponse> optionalAddressResponse = toAddressResponse(registerRequest.addressRequest());

        return new StudentResponse(
                fullName,
                registerRequest.age(),
                registerRequest.email(),
                0.0f,
                optionalAddressResponse);
    }
}
