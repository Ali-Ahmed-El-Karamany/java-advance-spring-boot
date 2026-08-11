package com.pioneers.universitysystem.utils;

import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.models.entities.Student;

public class Mappers {
    private Mappers() {

    }

    public static StudentResponse toStudentResponse(final Student student) {
        return new StudentResponse(student.getFullName(), student.getAge(), student.getEmail());
    }
}
