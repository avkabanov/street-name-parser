package com.kabanov.street_parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kabanov.street_parser.data.Address;
import com.kabanov.street_parser.io.reader.InputReader;
import com.kabanov.street_parser.io.writer.ResultPublisher;
import com.kabanov.street_parser.parer.Parser;

@ExtendWith(MockitoExtension.class)
class StreetHouseParserTest {

    @Mock private InputReader inputReader;
    @Mock private Parser parser;
    @Mock private ResultPublisher resultPublisher;

    @Captor private ArgumentCaptor<String> stringArgumentCaptor;
    @Captor private ArgumentCaptor<Address> addressArgumentCaptor;

    private StreetHouseParser streetHouseParser;

    @BeforeEach
    public void setup() {
        streetHouseParser = new StreetHouseParser(inputReader, resultPublisher, parser);
    }

    @Test
    public void shouldInputStringBePassedToParser() {
        String consoleInput = "Am Bächle 23";
        Mockito.when(inputReader.readLine()).thenReturn(consoleInput);

        streetHouseParser.start();

        Mockito.verify(parser, Mockito.times(1)).parse(stringArgumentCaptor.capture());
        Assertions.assertEquals(consoleInput, stringArgumentCaptor.getValue());
    }

    @Test
    public void shouldPassToPublisherWhenParseResultIsObtained() {
        Address parsedAddress = new Address("Am Bächle", "23");
        Mockito.when(parser.parse(Mockito.any())).thenReturn(parsedAddress);

        streetHouseParser.start();

        Mockito.verify(resultPublisher, Mockito.times(1)).publishResult(addressArgumentCaptor.capture());
        
        Assertions.assertEquals(parsedAddress, addressArgumentCaptor.getValue());
    }

}