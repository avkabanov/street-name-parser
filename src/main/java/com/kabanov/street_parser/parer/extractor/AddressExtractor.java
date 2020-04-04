package com.kabanov.street_parser.parer.extractor;

import com.kabanov.street_parser.parer.traversal.WordTraversal;

public class AddressExtractor implements WordExtractor {

    @Override
    public String extract(WordTraversal wordTraversal) {
        // for address we take the remaining part
        return wordTraversal.extract(0, wordTraversal.getWordsCount());
    }
}
