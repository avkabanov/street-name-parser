package com.kabanov.street_parser.formatter.json;

import javax.annotation.Nonnull;

import com.kabanov.street_parser.formatter.output.AddressOutput;

public interface AddressFormatter {
    
    String formatOutput(@Nonnull AddressOutput object);
}
