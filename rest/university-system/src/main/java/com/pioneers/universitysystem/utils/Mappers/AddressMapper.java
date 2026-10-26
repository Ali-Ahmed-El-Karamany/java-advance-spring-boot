package com.pioneers.universitysystem.utils.Mappers;

import com.pioneers.universitysystem.models.dtos.requests.AddressRequest;
import com.pioneers.universitysystem.models.dtos.responses.AddressResponse;
import com.pioneers.universitysystem.models.entities.Address;

import java.util.Optional;

public final class AddressMapper {
    private AddressMapper() {
        throw new AssertionError("Utility class");
    }

    public static Address toAddress(final AddressRequest addressRequest) {
        return Address.builder()
                .country(addressRequest.country())
                .governance(addressRequest.governance())
                .city(addressRequest.city())
                .street(addressRequest.street())
                .buildingNumber(addressRequest.buildingNumber())
                .floorNumber(addressRequest.floorNumber())
                .apartmentNumber(addressRequest.apartmentNumber())
                .build();
    }

    public static Optional<AddressResponse> toAddressResponse(final Address address) {
        if (address == null) {
            return Optional.empty();
        }

        return Optional.of(AddressResponse.builder()
                .country(address.getCountry())
                .governance(address.getGovernance())
                .city(address.getCity())
                .street(address.getStreet())
                .buildingNumber(address.getBuildingNumber())
                .floorNumber(address.getFloorNumber())
                .apartmentNumber(address.getApartmentNumber())
                .build());
    }

    public static Optional<AddressResponse> toAddressResponse(final AddressRequest addressRequest) {
        return Optional.of(AddressResponse.builder()
                .country(addressRequest.country())
                .governance(addressRequest.governance())
                .city(addressRequest.city())
                .street(addressRequest.street())
                .buildingNumber(addressRequest.buildingNumber())
                .floorNumber(addressRequest.floorNumber())
                .apartmentNumber(addressRequest.apartmentNumber())
                .build());
    }
}
