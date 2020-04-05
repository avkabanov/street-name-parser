package com.kabanov.street_parser.parer.traversal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface WordTraversal {

    @Nullable
    WordEntry moveToPreviousAndGet();

    @Nullable
    WordEntry moveToNextAndGet();

    @Nonnull
    WordEntry moveToBeginningAndGet();

    @Nonnull
    WordEntry moveToEndAndGet();
    
    @Nonnull
    /**
     * @throws IllegalArgumentException
     */
    WordEntry moveToPositionAndGet(int position);

    /**
     * @param from – the beginning word index, inclusive.
     * @param to   – the ending word index, exclusive.
     * @return string containing words in the given range
     */
    @Nonnull
    String extract(int from, int to);

    int getWordsCount();
    
    boolean isDelimiter(String string);

}
