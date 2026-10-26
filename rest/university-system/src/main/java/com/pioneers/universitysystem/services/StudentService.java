package com.pioneers.universitysystem.services;

import com.pioneers.universitysystem.errors.exceptions.StudentException;
import com.pioneers.universitysystem.models.dtos.requests.StudentUpdateRequest;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    List<StudentResponse> findAll() throws StudentException;

    StudentResponse findByEmail(final String email) throws StudentException;

    StudentResponse findById(UUID id) throws  StudentException;

    List<StudentResponse> findAllSucceeded() throws StudentException;

    StudentResponse update(UUID id,StudentUpdateRequest updateRequest) throws StudentException;

    StudentResponse delete(UUID id) throws StudentException;

    void deleteAll();
}
