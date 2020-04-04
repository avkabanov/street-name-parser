package com.kabanov.street_parser.parer.finder;

import javax.annotation.Nullable;

import com.kabanov.street_parser.parer.traversal.WordEntry;
import com.kabanov.street_parser.parer.traversal.WordTraversal;

public class HouseNumberFinder {

    public FindResult find(WordTraversal wordTraversal) {

        FindResult findResult = tryGetHouseNumberFromTheBeginnigOfTheString(wordTraversal);
        if (findResult != null) {
            return findResult;
        }

        return tryGetHouseNumberFromTheEndOfTheString(wordTraversal);
    }

    private FindResult tryGetHouseNumberFromTheEndOfTheString(
            WordTraversal wordTraversal) {
        WordEntry lastDigit = moveToLastDigit(wordTraversal);
        if (lastDigit == null) {
            return null;
        }

        WordEntry current;
        do {
            current = wordTraversal.moveToNext();
            if (current == null) {   // we reached the end 
                return new FindResult(lastDigit.getIndex(), wordTraversal.getWordsCount());
            }
        } while (isAPartOfAHouseNumber(current));
        return null;   // we return null because of assumption that house number can not be in the middle 

    }

    private FindResult tryGetHouseNumberFromTheBeginnigOfTheString(WordTraversal wordTraversal) {
        WordEntry firstDigit = moveToFirstDigit(wordTraversal);
        if (firstDigit == null) {
            return null;
        }

        WordEntry current;
        do {
            current = wordTraversal.moveToPrevious();
            if (current == null) {   // we reached the beginning
                return new FindResult(0, firstDigit.getIndex());
            }
        } while (isAPartOfAHouseNumber(current));
        return null;   // we return null because of assumption that house number can not be in the middle
    }

    private boolean isAPartOfAHouseNumber(WordEntry current) {
        return false;
    }

    @Nullable
    WordEntry moveToFirstDigit(WordTraversal wordTraversal) {
        WordEntry current = wordTraversal.moveToFirst();

        while (current != null && !isDigit(current.getValue())) {
            current = wordTraversal.moveToNext();
        }
        return current;
    }

    @Nullable
    WordEntry moveToLastDigit(WordTraversal wordTraversal) {
        WordEntry current = wordTraversal.moveToLast();

        while (current != null && !isDigit(current.getValue())) {
            current = wordTraversal.moveToPrevious();
        }
        return current;
    }

    private boolean isDigit(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
