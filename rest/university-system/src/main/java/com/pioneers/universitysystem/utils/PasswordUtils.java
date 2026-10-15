package com.pioneers.universitysystem.utils;

import com.pioneers.universitysystem.errors.exceptions.CredentialsExceptions;
import com.pioneers.universitysystem.errors.exceptions.PasswordMismatchException;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Slf4j
public final class PasswordUtils {

    private PasswordUtils() {
        throw new AssertionError("Utility Class");
    }

    public static String hashPassword(final String password) throws CredentialsExceptions {
        final String methodName = "hashPassword";

        Optional.ofNullable(password).
                orElseThrow(() -> {
                    final String logMessage = String.format("%s, Password can not be null", methodName);
                    return new CredentialsExceptions("Password cannot be null", logMessage);
                });

        byte[] hash;
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            hash = messageDigest.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            final String LogMessage = String.format("%s Hash Algorithm Not Supported", methodName);
            throw new CredentialsExceptions("SHA-256 algorithm not found", LogMessage);
        }

        return Base64.getEncoder().encodeToString(hash);
    }

    public static void matchPassword(
            final String password, final String storedPassword
    ) throws CredentialsExceptions, PasswordMismatchException {

        final String hashedPassword = hashPassword(password);

        if (!hashedPassword.equals(storedPassword)) {
            final String logMessage = "Password does not match for this email";

            throw new PasswordMismatchException("Wrong Password", logMessage);
        }

        log.debug("Password matched successfully");
    }
}
