package com.kabanov.street_parser.parer;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kabanov.street_parser.data.Address;
import com.kabanov.street_parser.parer.extractor.AddressExtractor;
import com.kabanov.street_parser.parer.extractor.HouseNumberExtractor;
import com.kabanov.street_parser.parer.finder.HouseNumberFinder;
import com.kabanov.street_parser.parer.traversal.WordTraversalImpl;

public class ParserTest {

    private Parser parser = new Parser(new HouseNumberExtractor(new HouseNumberFinder()), new AddressExtractor(),
            WordTraversalImpl::new);

    @ParameterizedTest
    @MethodSource("testDataSetProvider")
    public void shouldBeParsedCorrectly(String input, Address expectedParsedResult) {
        Address actual = parser.parse(input);
        Assertions.assertEquals(expectedParsedResult, actual);
    }

    static Stream<Arguments> testDataSetProvider() {
        return Stream.of(
                Arguments.of("Winterallee 3", new Address("Winterallee", "3")),
                Arguments.of("Musterstrasse 45", new Address("Musterstrasse", "45")),
                Arguments.of("Blaufeldweg 123B", new Address("Blaufeldweg", "123B")),
                Arguments.of("Am Bächle 23", new Address("Am Bächle", "23")),
                Arguments.of("Auf der Vogelwiese 23 b", new Address("Auf der Vogelwiese", "23 b")),
                Arguments.of("4, rue de la revolution", new Address("rue de la revolution", "4")),
                Arguments.of("200 Broadway Av", new Address("Broadway Av", "200")),
                Arguments.of("Calle Aduana, 29", new Address("Calle Aduana", "29")),
                Arguments.of("Calle 39 No 1540", new Address("Calle 39", "No 1540"))
        );
    }
}