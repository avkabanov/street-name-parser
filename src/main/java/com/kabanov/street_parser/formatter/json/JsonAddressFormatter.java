package com.kabanov.street_parser.formatter.json;

import javax.annotation.Nonnull;

import com.google.gson.Gson;
import com.kabanov.street_parser.formatter.output.AddressOutput;


/**
 * @author kabaale
 */
public class JsonAddressFormatter implements AddressFormatter {

    private Gson gson = new Gson();

    @Override
    public String formatOutput(@Nonnull AddressOutput address) {
        return gson.toJson(address);
    }
}
