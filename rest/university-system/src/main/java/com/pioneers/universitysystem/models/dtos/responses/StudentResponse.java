package com.pioneers.universitysystem.models.dtos.responses;

import java.util.Optional;

public record StudentResponse(
        String fullName,
        int age,
        String email,
        float score,
        Optional<AddressResponse> optionalAddressResponse
) {
}
