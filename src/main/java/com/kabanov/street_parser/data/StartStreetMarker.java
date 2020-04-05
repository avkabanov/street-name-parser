package com.kabanov.street_parser.data;

import java.util.Arrays;
import java.util.Optional;

public enum StartStreetMarker {
    NO("no");

    private String representation;

    StartStreetMarker(String representation) {
        this.representation = representation;
    }

    public static boolean isStartMarker(String value) {
        Optional<StartStreetMarker> result = Arrays.stream(values())
                .filter(startStreetMarker -> startStreetMarker.representation.equalsIgnoreCase(value))
                .findAny();
        return result.isPresent();
    }
    
}
