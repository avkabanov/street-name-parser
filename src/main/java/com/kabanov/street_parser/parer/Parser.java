package com.kabanov.street_parser.parer;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.kabanov.street_parser.data.Address;
import com.kabanov.street_parser.parer.extractor.WordExtractor;
import com.kabanov.street_parser.parer.traversal.WordTraversal;
import com.kabanov.street_parser.parer.traversal.WordTraversalFactory;

public class Parser {

    private WordExtractor houseNumberExtractor;
    private WordExtractor addressExtractor;
    private WordTraversalFactory wordTraversalFactory;

    @Inject
    public Parser(@Named("houseNumberExtractor") WordExtractor houseNumberExtractor,
                  @Named("addressExtractor") WordExtractor addressExtractor,
                  WordTraversalFactory wordTraversalFactory) {
        this.houseNumberExtractor = houseNumberExtractor;
        this.addressExtractor = addressExtractor;
        this.wordTraversalFactory = wordTraversalFactory;
    }

    public Address parse(String string) {

        WordTraversal wordTraversal = wordTraversalFactory.create(string);

        String house = houseNumberExtractor.extract(wordTraversal);
        String street = addressExtractor.extract(wordTraversal);

        return new Address(street, house);
    }
}
