package com.pioneers.designpatterns.builder;

public class Main {
    public static void main(String[] args) {
        final Address address = Address.builder()
                .continent("Africa")
                .country("Egypt")
                .governance("Cairo")
                .city("New Cairo")
                .zip(11111)
                .street("street")
                .buildingNumber(4)
                .floor(5)
                .apartmentNumber(123)
                .build();

        System.out.println(address);

        System.out.println("address.getContinent() = " + address.getContinent());
        System.out.println("address.getCountry() = " + address. getCountry());
        System.out.println("address.getGovernance() = " + address.getGovernance());
        System.out.println("address.getCity() = " + address.getCity());
        System.out.println("address.getZip() = " + address.getZip());
        System.out.println("address.getStreet() = " + address.getStreet());
        System.out.println("address.getBuildingNumber() = " + address.getBuildingNumber());
        System.out.println("address.getFloor() = " + address.getFloor());
        System.out.println("address.getApartmentNumber() = " + address.getApartmentNumber());
    }
}
