package com.kabanov.street_parser.parer.finder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kabanov.street_parser.parer.traversal.WordTraversal;
import com.kabanov.street_parser.parer.traversal.WordTraversalImpl;

class HouseNumberFinderTest {

    HouseNumberFinder houseNumberFinder = new HouseNumberFinder();
    
    @Test
    public void shouldFindHouseNumberWhenLocatedTheBeginning() {
        WordTraversal wordTraversal = new WordTraversalImpl("45 Musterstrasse");
        FindResult result = houseNumberFinder.find(wordTraversal);
        Assertions.assertEquals(0, result.beginIndex);
        Assertions.assertEquals(1, result.endIndex);
    }

    @Test
    public void shouldFindHouseNumberWhenLocatedInTheBeginningWithSpecialWord() {
        WordTraversal wordTraversal = new WordTraversalImpl("No 45 Musterstrasse");
        FindResult result = houseNumberFinder.find(wordTraversal);
        Assertions.assertEquals(0, result.beginIndex);
        Assertions.assertEquals(2, result.endIndex);
    }

    @Test
    public void shouldFindHouseNumberWhenLocatedInTheBeginningWithMergedBlock() {
        WordTraversal wordTraversal = new WordTraversalImpl("45A Musterstrasse");
        FindResult result = houseNumberFinder.find(wordTraversal);
        Assertions.assertEquals(0, result.beginIndex);
        Assertions.assertEquals(1, result.endIndex);
    }

    @Test
    public void shouldFindHouseNumberWhenLocatedTheEnd() {
        WordTraversal wordTraversal = new WordTraversalImpl("Musterstrasse 45");
        FindResult result = houseNumberFinder.find(wordTraversal);
        Assertions.assertEquals(1, result.beginIndex);
        Assertions.assertEquals(2, result.endIndex);
    }

    @Test
    public void shouldFindHouseNumberWhenLocatedInTheEndWithSpecialWord() {
        WordTraversal wordTraversal = new WordTraversalImpl("Musterstrasse No 45");
        FindResult result = houseNumberFinder.find(wordTraversal);
        Assertions.assertEquals(1, result.beginIndex);
        Assertions.assertEquals(3, result.endIndex);
    }

    @Test
    public void shouldFindHouseNumberWhenLocatedInTheEndWithMergedBlock() {
        WordTraversal wordTraversal = new WordTraversalImpl("Musterstrasse 45A");
        FindResult result = houseNumberFinder.find(wordTraversal);
        Assertions.assertEquals(1, result.beginIndex);
        Assertions.assertEquals(2, result.endIndex);
    }

    @Test
    public void shouldFindHouseNumberWhenLocatedInTheEndWithNumberInStreetName() {
        WordTraversal wordTraversal = new WordTraversalImpl("Calle 39 No 1540");
        FindResult result = houseNumberFinder.find(wordTraversal);
        Assertions.assertEquals(2, result.beginIndex);
        Assertions.assertEquals(4, result.endIndex);
    }

    @Test
    public void shouldFindHouseNumberWhenCommaIsInBeginning() {
        WordTraversal wordTraversal = new WordTraversalImpl("No 1540, Calle 39");
        FindResult result = houseNumberFinder.find(wordTraversal);
        Assertions.assertEquals(0, result.beginIndex);
        Assertions.assertEquals(2, result.endIndex);
    }

    @Test
    public void shouldFindHouseNumberWhenCommaIsInTheEnd() {
        WordTraversal wordTraversal = new WordTraversalImpl("Calle 39, No 1540");
        FindResult result = houseNumberFinder.find(wordTraversal);
        Assertions.assertEquals(3, result.beginIndex);
        Assertions.assertEquals(5, result.endIndex);
    }
}