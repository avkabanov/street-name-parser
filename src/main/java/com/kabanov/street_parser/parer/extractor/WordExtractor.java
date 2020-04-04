package com.kabanov.street_parser.parer.extractor;

import com.kabanov.street_parser.parer.traversal.WordTraversal;

public interface WordExtractor {
    
    String extract(WordTraversal wordTraversal);
}
