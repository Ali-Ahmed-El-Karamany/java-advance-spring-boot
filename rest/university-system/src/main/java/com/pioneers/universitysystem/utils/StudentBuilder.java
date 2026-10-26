package com.pioneers.universitysystem.utils;

import com.pioneers.universitysystem.models.dtos.requests.AddressRequest;
import com.pioneers.universitysystem.models.entities.Student;

import static com.pioneers.universitysystem.utils.Mappers.AddressMapper.toAddress;
import static com.pioneers.universitysystem.utils.TimeHelper.currentTimeStamp;

public final class StudentBuilder {

    private StudentBuilder() {
        throw new AssertionError("Utility Class");
    }

    public static Student buildRegisteredStudent(
            String fullName,
            int age,
            String email,
            String hashedPassword,
            AddressRequest addressRequest
    ) {
        return Student.builder()
                .fullName(fullName)
                .age(age)
                .email(email)
                .password(hashedPassword)
                .createdAt(currentTimeStamp())
                .isArchived(false)
                .address(toAddress(addressRequest))
                .build();

    }
}
