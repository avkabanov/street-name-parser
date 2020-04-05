package com.kabanov.street_parser.parer.extractor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kabanov.street_parser.parer.traversal.WordTraversal;
import com.kabanov.street_parser.parer.traversal.WordTraversalImpl;

class AddressExtractorTest {

    private AddressExtractor addressExtractor = new AddressExtractor();
    
    @Test
    public void shouldExtractAddressFromString() {
        WordTraversal wordTraversal = new WordTraversalImpl("test address");
        String expected = "test address";

        String actual = addressExtractor.extract(wordTraversal);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldExtractAddressWithoutCommasWhenTheyAreAtTheBeginningOrAnd() {
        WordTraversal wordTraversal = new WordTraversalImpl(",, test address,, ");
        String expected = "test address";

        String actual = addressExtractor.extract(wordTraversal);
        Assertions.assertEquals(expected, actual);
    }
}