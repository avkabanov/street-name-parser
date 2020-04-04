package com.kabanov.street_parser.io.writer;

import com.google.inject.Inject;
import com.kabanov.street_parser.data.Address;
import com.kabanov.street_parser.formatter.json.AddressFormatter;
import com.kabanov.street_parser.formatter.output.AddressOutput;

public class ResultPublisher {
    
    private AddressFormatter addressFormatter; 
    private OutputWriter outputWriter;

    @Inject
    public ResultPublisher(AddressFormatter addressFormatter, OutputWriter outputWriter) {
        this.addressFormatter = addressFormatter;
        this.outputWriter = outputWriter;
    }

    public void publishResult(Address address) {
        AddressOutput addressOutput = AddressOutput.fromAddress(address);
        String result = addressFormatter.formatOutput(addressOutput);
        outputWriter.write(result);
    }
}
