package com.kabanov.street_parser;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kabanov.street_parser.configuration.StreetHouseParserModule;

public class Launcher {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new StreetHouseParserModule());
        StreetHouseParser streetHouseParser = injector.getInstance(StreetHouseParser.class);
        streetHouseParser.start();
    }
}
