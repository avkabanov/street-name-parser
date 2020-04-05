package com.kabanov.street_parser.parer.finder;

import javax.annotation.Nullable;

import com.kabanov.street_parser.data.StartStreetMarker;
import com.kabanov.street_parser.parer.traversal.WordEntry;
import com.kabanov.street_parser.parer.traversal.WordTraversal;

public class HouseNumberFinder {

    public FindResult find(WordTraversal wordTraversal) {

        WordEntry firstDigit = moveToFirstDigit(wordTraversal);
        if (firstDigit == null) {
            return null; // no digits in the string found
        }

        FindResult result = tryGetHouseNumberAtPosition(wordTraversal, firstDigit);
        if (result != null) {
            return result;
        }

        WordEntry lastDigit = moveToLastDigit(wordTraversal);
        assert lastDigit != null;
        if (firstDigit.getIndex() == lastDigit.getIndex()) {
            // that means there is the only digit in a string that is not a house number.
            // no need to repeat the search for the same digit.
            return null;
        }
        
        return tryGetHouseNumberAtPosition(wordTraversal, lastDigit);
    }
    
    private FindResult tryGetHouseNumberAtPosition(WordTraversal wordTraversal, WordEntry startWordEntry) {
        int startPosition = tryToExpandToTheBeginning(wordTraversal, startWordEntry.getIndex());
        int endPosition = tryToExpandToTheEnd(wordTraversal, startWordEntry.getIndex());
        
        // if found result is not at the beginning or not at the end of the string - that is not a house number
        // because house number can not be in the middle
        if (startPosition != 0 && endPosition != wordTraversal.getWordsCount()) {
            return null;
        } else {
            return new FindResult(startPosition, endPosition);
        }
    }

    private int tryToExpandToTheEnd(WordTraversal wordTraversal, int startIndex) {
        wordTraversal.moveToPositionAndGet(startIndex);
        
        WordEntry current;
        do {
            current = wordTraversal.moveToNextAndGet();
            if (current == null) {   // we reached the end 
                return wordTraversal.getWordsCount();
            }
        } while (isAPartOfAHouseNumber(wordTraversal, current));
        return current.getIndex();   

    }

    private int tryToExpandToTheBeginning(WordTraversal wordTraversal, int startIndex) {
        wordTraversal.moveToPositionAndGet(startIndex);

        WordEntry current;
        do {
            current = wordTraversal.moveToPreviousAndGet();
            if (current == null) {   // we reached the beginning 
                return 0;
            }
            if (isMarkerOfStartHouseNumber(current)) {
                return current.getIndex(); 
            }
            
        } while (isAPartOfAHouseNumber(wordTraversal, current));
        return current.getIndex() + 1;
    }

    private boolean isMarkerOfStartHouseNumber(WordEntry current) {
        return StartStreetMarker.isStartMarker(current.getValue());
    }

    private boolean isAPartOfAHouseNumber(WordTraversal wordTraversal, WordEntry current) {
        String word = current.getValue();
        if (wordTraversal.isDelimiter(word)) {
            return false;
        }
        
        return word.length() == 1 || wordContainsNumbers(word); 
    }

    private boolean wordContainsNumbers(String word) {
        for (Character ch : word.toCharArray()) {
            if (Character.isDigit(ch)) {
                return true;
            }    
        }
        
        return false;
    }

    @Nullable
    WordEntry moveToFirstDigit(WordTraversal wordTraversal) {
        WordEntry current = wordTraversal.moveToBeginningAndGet();

        while (current != null && !wordContainsNumbers(current.getValue())) {
            current = wordTraversal.moveToNextAndGet();
        }
        return current;
    }

    @Nullable
    WordEntry moveToLastDigit(WordTraversal wordTraversal) {
        WordEntry current = wordTraversal.moveToEndAndGet();

        while (current != null && !wordContainsNumbers(current.getValue())) {
            current = wordTraversal.moveToPreviousAndGet();
        }
        return current;
    }
}
