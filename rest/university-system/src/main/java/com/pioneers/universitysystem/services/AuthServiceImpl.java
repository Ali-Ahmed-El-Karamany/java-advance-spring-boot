package com.pioneers.universitysystem.services;

import com.pioneers.universitysystem.errors.exceptions.*;
import com.pioneers.universitysystem.models.dtos.requests.StudentLoginRequest;
import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;
import com.pioneers.universitysystem.models.dtos.responses.GenericResponse;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.models.entities.Student;
import com.pioneers.universitysystem.repositories.StudentRepository;
import com.pioneers.universitysystem.utils.Mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.pioneers.universitysystem.utils.NameBuilder.buildFullName;
import static com.pioneers.universitysystem.utils.PasswordUtils.hashPassword;
import static com.pioneers.universitysystem.utils.PasswordUtils.isPasswordMatched;
import static com.pioneers.universitysystem.utils.StudentBuilder.buildRegisteredStudent;
import static com.pioneers.universitysystem.utils.validators.StudentValidator.validateRegisterRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;

    @Override
    public void signup(final StudentRegisterRequest registerRequest) throws ValidationException, RegistrationException {
        final String methodName = "signup";

        final Map<String, String> errors = validateRegisterRequest(registerRequest);

        if (!errors.isEmpty()) {
            final String logMessage = String.format("%s, Registration failed for email: %s due to: %s",
                    methodName, registerRequest.email(), errors);

            throw new ValidationException("Invalid Registration Data", logMessage, errors);
        }

        studentRepository.findByEmail(registerRequest.email()).ifPresent(student -> {
            final String logMessage = String.format("%s, Registration failed: [%s] is already registered",
                    methodName, student.getEmail());

            throw new RegistrationException(
                    String.format("Student with email [%s] is already registered", registerRequest.email()), logMessage);
        });

        final String fullName = buildFullName(registerRequest.firstName(), registerRequest.lastName());

        final String hashedPassword;
        try {
            hashedPassword = hashPassword(registerRequest.password());
        } catch (CredentialsExceptions e) {
            final String logMessage = String.format("%s, Cannot hash password for  [%s] due to: [%s]",
                    methodName, registerRequest.email(), e.getMessage());

            throw new RegistrationException(e.getMessage(), logMessage);
        }

        final Student student = buildRegisteredStudent(fullName, registerRequest.age(),
                registerRequest.email(), hashedPassword);

        studentRepository.save(student);

        log.debug("{}, {} successfully registered with email [{}]",
                methodName, student.getFullName(), student.getEmail());
    }

    @Override
    public void login(final StudentLoginRequest loginRequest) throws LoginException {

        final String methodName = "login";

        final Student foundStudent = studentRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> {
                    final String logMessage = String.format("%s, Student with email [%s] is not registered",
                            methodName, loginRequest.email());

                    return new LoginException("Student is not registered", logMessage);
                });

        try {
            if (!isPasswordMatched(loginRequest.password(), foundStudent.getPassword())) {
                final String logMessage = String.format("%s, password does not match for this email [%s]",
                        methodName, loginRequest.email());

                throw new LoginException("Wrong Password", logMessage);
            }
        } catch (CredentialsExceptions e) {
            final String logMessage = String.format("%s, Cannot hash password for  [%s] due to: [%s]",
                    methodName, loginRequest.email(), e.getMessage());

            throw new LoginException(e.getMessage(), logMessage);
        }

        if (foundStudent.isLoggedIn()) {
            final String logMessage = String.format("%s, Student with email [%s] already logged in",
                    methodName, loginRequest.email());

            throw new LoginException("Student already logged in", logMessage);
        }

        foundStudent.login();

        log.debug("{}, Student with email: {} logged in successfully", methodName, foundStudent.getEmail());
    }

    @Override
    public void logout(final String email) throws LogoutException {

        final String methodName = "logout";

        final Student foundStudent = studentRepository.findByEmail(email)
                .orElseThrow(() -> {
                    final String logMessage = String.format("%s, Student with email [%s] is not registered",
                            methodName, email);

                    return new LoginException("Student not registered", logMessage);
                });

        if (!foundStudent.isLoggedIn()) {
            final String logMessage = String.format("%s, Student with email [%s] already logged out",
                    methodName, email);

            throw new LogoutException("Student already logged out", logMessage);
        }

        foundStudent.logout();

        log.debug("{}, Student with email: {} logged out successfully", methodName, foundStudent.getEmail());
    }

    @Override
    public GenericResponse<List<StudentResponse>> saveAll(
            final List<StudentRegisterRequest> registerRequests) throws ValidationException, RegistrationException {
        final String methodName = "saveAll";

        final List<Student> registeredStudents = new ArrayList<>();

        registerRequests.forEach(registerRequest -> {
            studentRepository.findByEmail(registerRequest.email())
                    .ifPresentOrElse(registeredStudents::add, () -> signup(registerRequest));
        });

        if (registeredStudents.isEmpty()) {
            log.debug("Successfully registered all students");

            return new GenericResponse<>("Successfully registered all students",
                    registerRequests.stream().map(Mappers::toStudentResponse).toList());
        }

        final List<StudentResponse> rejectedStudents = registeredStudents.stream()
                .map(Mappers::toStudentResponse)
                .toList();

        log.debug("Those students are already registered {}", rejectedStudents);
        return new GenericResponse<>("Those students are already registered registered", rejectedStudents);
    }
}
