package com.pioneers.universitysystem.utils;

import com.pioneers.universitysystem.errors.exceptions.CredentialsExceptions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

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

    public static boolean isPasswordMatched(
            final String password, final String storedPassword
    ) throws CredentialsExceptions {
        final String hashedPassword = hashPassword(password);
        return hashedPassword.equals(storedPassword);
    }
}
