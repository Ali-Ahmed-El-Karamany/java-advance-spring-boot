package com.pioneers.universitysystem.errors.handlers;

import com.pioneers.universitysystem.errors.exceptions.CredentialsExceptions;
import com.pioneers.universitysystem.errors.exceptions.LoginException;
import com.pioneers.universitysystem.errors.exceptions.LogoutException;
import com.pioneers.universitysystem.errors.exceptions.RegistrationException;
import com.pioneers.universitysystem.errors.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class AuthExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegistrationException.class)
    public ErrorResponse<?> handleRegisterException(final RegistrationException e) {
        return ErrorResponse.builder()
                .status(RegistrationException.STATUS_CODE)
                .error(RegistrationException.ERROR)
                .description(e.getMessage())
                .timestamp(e.getTimestamp())
                .errors(Optional.empty())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginException.class)
    public ErrorResponse<?> handleLoginException(final LoginException e) {
        return ErrorResponse.builder()
                .status(LoginException.STATUS_CODE)
                .error(LoginException.ERROR)
                .description(e.getMessage())
                .timestamp(e.getTimestamp())
                .errors(Optional.empty())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LogoutException.class)
    public ErrorResponse<?> handleLogoutException(final LogoutException e) {
        return ErrorResponse.builder()
                .status(LogoutException.STATUS_CODE)
                .error(LogoutException.ERROR)
                .description(e.getMessage())
                .timestamp(e.getTimestamp())
                .errors(Optional.empty())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CredentialsExceptions.class)
    public ErrorResponse<?> handleCredentialsExceptions(final CredentialsExceptions e) {
        return ErrorResponse.builder()
                .status(CredentialsExceptions.STATUS_CODE)
                .error(CredentialsExceptions.ERROR)
                .description(e.getMessage())
                .timestamp(e.getTimestamp())
                .errors(Optional.empty())
                .build();
    }
}
