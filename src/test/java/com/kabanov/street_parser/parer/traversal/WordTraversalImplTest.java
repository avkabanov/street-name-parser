package com.kabanov.street_parser.parer.traversal;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordTraversalImplTest {

    @Test
    public void shouldGetCorrectWordOrderWhenGetAndMoveNextIsInvoked() {

        WordTraversal wordTraversal = new WordTraversalImpl("one two, three four");
        List<WordEntry> expected = List.of(
                new WordEntry(0, "one"),
                new WordEntry(1, "two"),
                new WordEntry(2, ","),
                new WordEntry(3, "three"),
                new WordEntry(4, "four")
        );

        List<WordEntry> actual = new ArrayList<>();

        actual.add(wordTraversal.moveToBeginningAndGet());
        WordEntry current;
        while ((current = wordTraversal.moveToNextAndGet()) != null) {
            actual.add(current);
        }

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetCorrectWordOrderWhenGetAndMovePreviousIsInvoked() {

        WordTraversal wordTraversal = new WordTraversalImpl("one two, three four");
        List<WordEntry> expected = List.of(
                new WordEntry(4, "four"),
                new WordEntry(3, "three"),
                new WordEntry(2, ","),
                new WordEntry(1, "two"),
                new WordEntry(0, "one")
        );

        List<WordEntry> actual = new ArrayList<>();
        actual.add(wordTraversal.moveToEndAndGet());
        
        WordEntry current;
        while ((current = wordTraversal.moveToPreviousAndGet()) != null) {
            actual.add(current);
        }

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenEmptyStringIsGiven() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new WordTraversalImpl(""));
    }

    @Test
    public void shouldReturnFirstWordWhenMoveToBeginningIsInvoked() {
        WordTraversal wordTraversal = new WordTraversalImpl("one two three");
        Assertions.assertEquals(new WordEntry(0, "one"), wordTraversal.moveToBeginningAndGet());
        Assertions.assertEquals(new WordEntry(1, "two"), wordTraversal.moveToNextAndGet());

        Assertions.assertEquals(new WordEntry(0, "one"), wordTraversal.moveToBeginningAndGet());
        Assertions.assertEquals(new WordEntry(1, "two"), wordTraversal.moveToNextAndGet());
    }

    @Test
    public void shouldReturnLastWordWhenMoveToEndIsInvoked() {
        WordTraversal wordTraversal = new WordTraversalImpl("one two three");
        Assertions.assertEquals(new WordEntry(2, "three"), wordTraversal.moveToEndAndGet());
        Assertions.assertEquals(new WordEntry(1, "two"), wordTraversal.moveToPreviousAndGet());
        
        Assertions.assertEquals(new WordEntry(2, "three"), wordTraversal.moveToEndAndGet());
        Assertions.assertEquals(new WordEntry(1, "two"), wordTraversal.moveToPreviousAndGet());
    }

    @Test
    public void shouldMoveToPositionMoveToSpecificPosition() {
        WordTraversal wordTraversal = new WordTraversalImpl("one two three");
        Assertions.assertEquals(new WordEntry(1, "two"), wordTraversal.moveToPositionAndGet(1));
    }

    @Test
    public void shouldMoveToPositionThrowExceptionWhenInvalidPostiionGiven() {
        WordTraversal wordTraversal = new WordTraversalImpl("one two three");
        Assertions.assertThrows(IllegalArgumentException.class, () -> wordTraversal.moveToPositionAndGet(100));
    }

    @Test
    public void shouldExtractTheSpecifiedWodsWhenSubIndexesIsGiven() {
        WordTraversal wordTraversal = new WordTraversalImpl("one two three four five");
        Assertions.assertEquals("two three four", wordTraversal.extract(1, 4));
    }
}