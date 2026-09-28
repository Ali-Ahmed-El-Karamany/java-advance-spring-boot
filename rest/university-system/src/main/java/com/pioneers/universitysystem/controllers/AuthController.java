package com.pioneers.universitysystem.controllers;

import com.pioneers.universitysystem.errors.exceptions.LoginException;
import com.pioneers.universitysystem.errors.exceptions.LogoutException;
import com.pioneers.universitysystem.errors.exceptions.RegistrationException;
import com.pioneers.universitysystem.errors.exceptions.ValidationException;
import com.pioneers.universitysystem.models.dtos.requests.StudentLoginRequest;
import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;
import com.pioneers.universitysystem.models.dtos.responses.GenericResponse;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.services.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<List<String>> signupApi(
            @RequestBody final StudentRegisterRequest registerRequest) {

        final String methodName = "signupApi";
        log.info("{}, Registering [{}]", methodName, registerRequest.email());

        try {
            authService.signup(registerRequest);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getErrors());
        } catch (RegistrationException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));
        }

        log.info("{}, Successfully registered student with email [{}]", methodName, registerRequest.email());

        return ResponseEntity.ok(List.of("Student Registered Successfully with email: " + registerRequest.email()));
    }

    @PostMapping("login")
    public ResponseEntity<String> loginApi(@RequestBody StudentLoginRequest loginRequest) {

        final String methodName = "loginApi";
        log.info("{}, Login [{}]", methodName, loginRequest.email());

        try {
            authService.login(loginRequest);
        } catch (LoginException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        log.info("{}, Student successfully logged in with email [{}]", methodName, loginRequest.email());

        return ResponseEntity.ok().body("Student logged in successfully with email: " + loginRequest.email());
    }

    @PostMapping("logout/{studentEmail}")
    public ResponseEntity<String> logoutApi(@PathVariable("studentEmail") String email) {

        final String methodName = "logoutApi";
        log.info("{}, Logout [{}]", methodName, email);

        try {
            authService.logout(email);

            log.info("{}, Student with email [{}] successfully logged out", methodName, email);

            return ResponseEntity.ok().body("Student with email [" + email + " ] logged out successfully");
        } catch (LogoutException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("saveAll")
    public ResponseEntity<?> saveAllApi(
            @RequestBody List<StudentRegisterRequest> registerRequests
    ) {
        final String methodName = "saveAllApi";
        log.info("{}, Saving [{}]", methodName, registerRequests);

        try {
            GenericResponse<List<StudentResponse>> response = authService.saveAll(registerRequests);

            log.info("{}, Successfully saved student [{}]", methodName, registerRequests);

            return ResponseEntity.ok(response);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getErrors());
        } catch (RegistrationException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));
        }
    }
}
