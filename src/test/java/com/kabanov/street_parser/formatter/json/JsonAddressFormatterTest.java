package com.kabanov.street_parser.formatter.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kabanov.street_parser.formatter.output.AddressOutput;

class JsonAddressFormatterTest {
    
    private JsonAddressFormatter formatter = new JsonAddressFormatter();

    @Test
    public void shouldProperlyFormatWhenAddressIsGiven() {

        AddressOutput address = new AddressOutput("test street", "45 A");
        String expected = "{\"street\":\"test street\",\"housenumber\":\"45 A\"}";
        String actual = formatter.formatOutput(address);

        Assertions.assertEquals(expected, actual);
        
    }

}