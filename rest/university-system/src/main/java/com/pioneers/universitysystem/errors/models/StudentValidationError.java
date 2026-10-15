package com.pioneers.universitysystem.errors.models;

import java.util.List;

public record StudentValidationError(int StudentNumber, List<ErrorDescription> errors) {
}
