package com.pioneers.universitysystem.utils;

import com.pioneers.universitysystem.errors.exceptions.CredentialsExceptions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

public class PasswordUtils {
    private PasswordUtils() {
    }

    public static String hashPassword(final String password) throws CredentialsExceptions {
        Optional.ofNullable(password).
                orElseThrow(() -> new CredentialsExceptions("Password cannot be null"));

        byte[] hash;
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            hash = messageDigest.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new CredentialsExceptions("SHA-256 algorithm not found");
        }

        return Base64.getEncoder().encodeToString(hash);
    }
}
