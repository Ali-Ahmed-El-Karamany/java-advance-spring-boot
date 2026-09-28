package com.pioneers.universitysystem.services;

import com.pioneers.universitysystem.errors.exceptions.*;
import com.pioneers.universitysystem.models.dtos.requests.StudentLoginRequest;
import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;
import com.pioneers.universitysystem.models.dtos.responses.GenericResponse;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;

import java.util.List;

public interface AuthService {

    void signup(StudentRegisterRequest registerRequest) throws ValidationException, RegistrationException;

    void login(StudentLoginRequest loginRequest) throws LoginException;

    void logout(String email) throws LogoutException;

    GenericResponse<List<StudentResponse>> saveAll(
            List<StudentRegisterRequest> registerRequests) throws ValidationException, RegistrationException;
}
