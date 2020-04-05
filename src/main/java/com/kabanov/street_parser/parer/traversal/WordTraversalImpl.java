package com.kabanov.street_parser.parer.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class WordTraversalImpl implements WordTraversal {

    private static final String WORDS_SEPARATOR = " ";
    private static final Set<String> preservedDelimiters = Set.of(",");

    private List<String> words;
    private int currentWord = 0;

    @Inject
    public WordTraversalImpl(@Assisted @Nonnull String string) {
        string = addWordSeparatorBetweenPreservedDelimiters(string);

        if (string.isEmpty()) {
            throw new IllegalArgumentException("String can not be empty");
        }
        // we make an own instance of array list because we need to modify the list, but
        // we can not do it with the view, that is created by Arrays.asList
        words = new ArrayList<>(Arrays.asList(string.split(WORDS_SEPARATOR)));
    }

    private String addWordSeparatorBetweenPreservedDelimiters(String string) {
        for (String preservedDelimeter : preservedDelimiters) {
            String replaceChar = WORDS_SEPARATOR + preservedDelimeter + WORDS_SEPARATOR;                 
            string = string.replace(preservedDelimeter, replaceChar);
        }

        string = string.replaceAll(" +", " "); // remove all duplicated spaces
        
        return string.trim();
    }

    @Nullable
    @Override
    public WordEntry getAndMoveToPrevious() {
        if (currentWord < 0) {
            return null;
        } else {
            return createCurrentWordEntry(currentWord--);
        }
    }

    @Nullable
    @Override
    public WordEntry getAndMoveToNext() {
        if (currentWord == words.size()) {
            return null;
        } else {
            return createCurrentWordEntry(currentWord++);
        }
    }

    @Nonnull
    @Override
    public WordEntry moveToBeginningAndGet() {
        currentWord = 0;
        return createCurrentWordEntry(currentWord);
    }

    @Nonnull
    @Override
    public WordEntry moveToEndAndGet() {
        currentWord = getWordsCount() - 1;
        return createCurrentWordEntry(currentWord);
    }

    @Nonnull
    @Override
    public WordEntry moveToPositionAndGet(int position) {
        checkIndex(position);
        currentWord = position;
        return createCurrentWordEntry(currentWord);
    }

    private void checkIndex(int position) {
        if (position < 0 || position > getWordsCount()) {
            throw new IllegalArgumentException("Invalid index: " + position);
        } 
    }

    private void checkRange(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("from > to: " + from + " " + to);
        }
        checkIndex(from);
        checkIndex(to);
    }

    @Nonnull
    @Override
    public String extract(int from, int to) {
        checkRange(from, to);
        StringBuilder result = new StringBuilder();
        for (int i = from; i < to; i++) {
            result.append(words.get(i)).append(WORDS_SEPARATOR);
        }
        // remove the last separator
        result.deleteCharAt(result.length() - 1);

        words.subList(from, to).clear();

        return result.toString();
    }

    @Override
    public int getWordsCount() {
        return words.size();
    }

    @Override
    public boolean isDelimiter(String string) {
        return preservedDelimiters.contains(string);
    }

    private WordEntry createCurrentWordEntry(int index) {
        return new WordEntry(index, words.get(index));
    }
}
