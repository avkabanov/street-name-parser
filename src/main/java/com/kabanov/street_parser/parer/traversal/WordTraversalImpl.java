package com.kabanov.street_parser.parer.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class WordTraversalImpl implements WordTraversal {

    private static final String WORDS_SEPARATOR = " ";

    private List<String> words;
    private int currentWord = 0;

    public WordTraversalImpl(String string) {
        // we make an own instance of array list because we need to modify the list, but
        // we can not do it with the view, that is created by Arrays.asList
        words = new ArrayList<>(Arrays.asList(string.split(WORDS_SEPARATOR)));
    }

    @Nullable
    @Override
    public WordEntry moveToPrevious() {
        if (currentWord == 0) {
            return null;
        } else {
            currentWord--;
            return createCurrentWordEntry();
        }
    }

    @Nullable
    @Override
    public WordEntry moveToNext() {
        if (currentWord == words.size() - 1) {
            return null;
        } else {
            currentWord++;
            return createCurrentWordEntry();
        }
    }

    @Nullable
    @Override
    public WordEntry moveToFirst() {
        currentWord = 0;
        return createCurrentWordEntry();
    }

    @Nullable
    @Override
    public WordEntry moveToLast() {
        currentWord = getWordsCount() - 1;
        return createCurrentWordEntry();
    }

    @Override
    public String extract(int from, int to) {
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

    private WordEntry createCurrentWordEntry() {
        return new WordEntry(currentWord, words.get(currentWord));
    }
}
