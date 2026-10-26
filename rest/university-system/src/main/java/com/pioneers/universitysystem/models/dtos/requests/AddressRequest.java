package com.pioneers.universitysystem.models.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressRequest(
        @NotBlank(message = "{validation.country.blank}")
        @Size(min = 2, max = 30, message = "{validation.country.size}")
        String country,

        @NotBlank(message = "{validation.governance.blank}")
        @Size(min = 2, max = 30, message = "{validation.governance.size}")
        String governance,

        @NotBlank(message = "{validation.city.blank}")
        @Size(min = 2, max = 30, message = "{validation.city.size}")
        String city,

        @NotBlank(message = "{validation.street.blank}")
        @Size(min = 2, max = 255, message = "{validation.street.size}")
        String street,

        @Min(value = 1, message = "{validation.building-number.min-value}")
        int buildingNumber,

        @Min(value = 1, message = "{validation.floor-number.min-value}")
        int floorNumber,

        @Min(value = 1, message = "{validation.apartment-number.min-value}")
        int apartmentNumber
) {
}
