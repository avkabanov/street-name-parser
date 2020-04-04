package com.kabanov.street_parser.data;

import java.util.Objects;

public class Address {
    
    public String street;
    public String house;

    public Address(String street, String house) {
        this.street = street;
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(house, address.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, house);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", house='" + house + '\'' +
                '}';
    }
}
