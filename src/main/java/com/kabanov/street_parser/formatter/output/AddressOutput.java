package com.kabanov.street_parser.formatter.output;

import com.google.gson.annotations.SerializedName;
import com.kabanov.street_parser.data.Address;

public class AddressOutput {
                            
    @SerializedName(value = "street")
    private String streetName;
    @SerializedName(value = "housenumber")
    private String houseNumber;

    public AddressOutput() {
    }

    public AddressOutput(String streetName, String houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    public static AddressOutput fromAddress(Address address) {
        return new AddressOutput(address.street, address.house);
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
