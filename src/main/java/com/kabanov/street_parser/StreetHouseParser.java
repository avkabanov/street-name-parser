package com.kabanov.street_parser;

import com.kabanov.street_parser.data.Address;
import com.kabanov.street_parser.io.reader.InputReader;
import com.kabanov.street_parser.io.writer.ResultPublisher;
import com.kabanov.street_parser.parer.Parser;

public class StreetHouseParser {
    
    private InputReader reader;
    private ResultPublisher publisher;
    private Parser parser;
    
    public void start() {
        String input = reader.readLine();
        Address address = parser.parse(input);
        publisher.publishResult(address);
    }
}
