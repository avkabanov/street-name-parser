package com.kabanov.street_parser.parer.traversal;

import javax.annotation.Nullable;

public interface WordTraversal {

    @Nullable
    WordEntry moveToPrevious();

    @Nullable
    WordEntry moveToNext();

    @Nullable
    WordEntry moveToFirst();

    @Nullable
    WordEntry moveToLast();

    /**
     * @param from – the beginning word index, inclusive.
     * @param to   – the ending word index, exclusive.
     * @return string containing words in the given range
     */
    String extract(int from, int to);

    int getWordsCount();

}
