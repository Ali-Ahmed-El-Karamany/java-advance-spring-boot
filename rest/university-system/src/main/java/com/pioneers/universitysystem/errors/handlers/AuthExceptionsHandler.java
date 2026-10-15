package com.pioneers.universitysystem.errors.handlers;

import com.pioneers.universitysystem.errors.exceptions.*;
import com.pioneers.universitysystem.errors.models.ErrorDescription;
import com.pioneers.universitysystem.errors.models.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegistrationException.class)
    public ErrorResponse<ErrorDescription> handleRegistrationException(final RegistrationException e) {
        return new ErrorResponse<>(RegistrationException.STATUS_CODE, e.getTimestamp(),
                new ErrorDescription(RegistrationException.ERROR, e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginException.class)
    public ErrorResponse<ErrorDescription> handleLoginException(final LoginException e) {
        return new ErrorResponse<>(LoginException.STATUS_CODE, e.getTimestamp(),
                new ErrorDescription(LoginException.ERROR, e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LogoutException.class)
    public ErrorResponse<ErrorDescription> handleLogoutException(final LogoutException e) {
        return new ErrorResponse<>(LogoutException.STATUS_CODE, e.getTimestamp(),
                new ErrorDescription(LogoutException.ERROR, e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordMismatchException.class)
    public ErrorResponse<ErrorDescription> handlePasswordMismatchException(final PasswordMismatchException e) {
        return new ErrorResponse<>(PasswordMismatchException.STATUS_CODE, e.getTimestamp(),
                new ErrorDescription(PasswordMismatchException.ERROR, e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CredentialsExceptions.class)
    public ErrorResponse<ErrorDescription> handleCredentialsExceptions(final CredentialsExceptions e) {
        return new ErrorResponse<>(CredentialsExceptions.STATUS_CODE, e.getTimestamp(),
                new ErrorDescription(CredentialsExceptions.ERROR, e.getMessage()));
    }
}
