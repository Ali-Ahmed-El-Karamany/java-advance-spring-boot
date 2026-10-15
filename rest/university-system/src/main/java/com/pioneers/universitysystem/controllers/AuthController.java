package com.pioneers.universitysystem.controllers;

import com.pioneers.universitysystem.models.dtos.requests.StudentLoginRequest;
import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;
import com.pioneers.universitysystem.models.dtos.responses.GenericResponse;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("signup")
    public String signupApi(@RequestBody @Valid final StudentRegisterRequest registerRequest) {
        final String methodName = "signupApi";
        log.info("{}, Registering [{}]", methodName, registerRequest.email());

        authService.signup(registerRequest);

        log.info("{}, Successfully registered student with email [{}]", methodName, registerRequest.email());

        return "Student Registered Successfully with email: " + registerRequest.email();
    }

    @PostMapping("login")
    public String loginApi(@RequestBody @Valid StudentLoginRequest loginRequest) {
        final String methodName = "loginApi";
        log.info("{}, Login [{}]", methodName, loginRequest.email());

        authService.login(loginRequest);

        log.info("{}, Student successfully logged in with email [{}]", methodName, loginRequest.email());

        return "Student logged in successfully with email: " + loginRequest.email();
    }

    @PostMapping("logout/{studentEmail}")
    public String logoutApi(@PathVariable("studentEmail") String email) {
        final String methodName = "logoutApi";
        log.info("{}, Logout [{}]", methodName, email);

        authService.logout(email);

        log.info("{}, Student with email [{}] successfully logged out", methodName, email);

        return "Student with email [" + email + "] logged out successfully";
    }

    @PostMapping("saveAll")
    public GenericResponse<List<StudentResponse>> saveAllApi(
            @RequestBody List<@Valid StudentRegisterRequest> registerRequests
    ) {
        final String methodName = "saveAllApi";
        log.info("{}, Saving [{}]", methodName, registerRequests);

        GenericResponse<List<StudentResponse>> response = authService.saveAll(registerRequests);

        log.info("{}, Successfully saved student [{}]", methodName, registerRequests);

        return response;
    }
}
