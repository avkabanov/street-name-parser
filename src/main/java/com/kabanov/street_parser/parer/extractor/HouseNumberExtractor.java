package com.kabanov.street_parser.parer.extractor;

import com.google.inject.Inject;
import com.kabanov.street_parser.parer.finder.FindResult;
import com.kabanov.street_parser.parer.finder.HouseNumberFinder;
import com.kabanov.street_parser.parer.traversal.WordTraversal;

public class HouseNumberExtractor implements WordExtractor {

    private HouseNumberFinder finder;

    @Inject
    public HouseNumberExtractor(HouseNumberFinder finder) {
        this.finder = finder;
    }

    @Override
    public String extract(WordTraversal wordTraversal) {

        FindResult result = finder.find(wordTraversal);
        if (result == null) {
            return null;
        }

        return wordTraversal.extract(result.getBeginIndex(), result.getEndingIndex());



    }

}
