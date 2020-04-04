package com.kabanov.street_parser.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.kabanov.street_parser.parer.traversal.WordTraversal;
import com.kabanov.street_parser.parer.traversal.WordTraversalFactory;
import com.kabanov.street_parser.parer.traversal.WordTraversalImpl;

public class StreetHouseParserModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(WordTraversal.class, WordTraversalImpl.class)
                .build(WordTraversalFactory.class));
    }
}
