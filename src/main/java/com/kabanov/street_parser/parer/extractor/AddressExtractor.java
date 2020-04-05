package com.kabanov.street_parser.parer.extractor;

import com.kabanov.street_parser.parer.traversal.WordEntry;
import com.kabanov.street_parser.parer.traversal.WordTraversal;

public class AddressExtractor implements WordExtractor {

    @Override
    public String extract(WordTraversal wordTraversal) {
        int startIndex = getStartIndex(wordTraversal);
        int endIndex = getEndIndex(wordTraversal);
        return wordTraversal.extract(startIndex, endIndex);
    }

    /**
     * @return end index exclusive
     */
    private int getEndIndex(WordTraversal wordTraversal) {
        WordEntry current = wordTraversal.moveToEndAndGet();
        while (wordTraversal.isDelimiter(current.getValue())) {
            current = wordTraversal.getAndMoveToPrevious();
        }
        return current.getIndex() + 1;
    }

    /**
     * @return start index inclusive
     */
    private int getStartIndex(WordTraversal wordTraversal) {
        WordEntry current = wordTraversal.moveToBeginningAndGet();
        while (wordTraversal.isDelimiter(current.getValue())) {
            current = wordTraversal.getAndMoveToNext();
        }
        return current.getIndex();
    }
}
