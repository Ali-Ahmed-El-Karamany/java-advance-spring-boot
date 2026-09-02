package com.pioneers.designpatterns.builder;

import lombok.Value;

@Value
public class Address {
    String continent;
    String country;
    String governance;
    String city;
    int zip;
    String street;
    int buildingNumber;
    int floor;
    int apartmentNumber;

    private Address(AddressBuilder builder) {
        this.continent = builder.continent;
        this.country = builder.country;
        this.governance = builder.governance;
        this.city = builder.city;
        this.zip = builder.zip;
        this.street = builder.street;
        this.buildingNumber = builder.buildingNumber;
        this.floor = builder.floor;
        this.apartmentNumber = builder.apartmentNumber;
    }

    public static AddressBuilder builder() {
        return new AddressBuilder();
    }


    public static class AddressBuilder {
        private String continent;
        private String country;
        private String governance;
        private String city;
        private int zip;
        private String street;
        private int buildingNumber;
        private int floor;
        private int apartmentNumber;

        public AddressBuilder continent(String continent) {
            this.continent = continent;
            return this;
        }

        public AddressBuilder country(String country) {
            this.country = country;
            return this;
        }

        public AddressBuilder governance(String governance) {
            this.governance = governance;
            return this;
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder zip(int zip) {
            this.zip = zip;
            return this;
        }

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder buildingNumber(int buildingNumber) {
            this.buildingNumber = buildingNumber;
            return this;
        }

        public AddressBuilder floor(int floor) {
            this.floor = floor;
            return this;
        }

        public AddressBuilder apartmentNumber(int apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }


}
