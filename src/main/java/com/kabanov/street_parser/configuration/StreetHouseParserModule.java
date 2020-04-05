package com.kabanov.street_parser.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import com.kabanov.street_parser.formatter.json.AddressFormatter;
import com.kabanov.street_parser.formatter.json.JsonAddressFormatter;
import com.kabanov.street_parser.io.reader.ConsoleReader;
import com.kabanov.street_parser.io.reader.InputReader;
import com.kabanov.street_parser.io.writer.ConsoleWriter;
import com.kabanov.street_parser.io.writer.OutputWriter;
import com.kabanov.street_parser.parer.extractor.AddressExtractor;
import com.kabanov.street_parser.parer.extractor.HouseNumberExtractor;
import com.kabanov.street_parser.parer.extractor.WordExtractor;
import com.kabanov.street_parser.parer.traversal.WordTraversal;
import com.kabanov.street_parser.parer.traversal.WordTraversalFactory;
import com.kabanov.street_parser.parer.traversal.WordTraversalImpl;

public class StreetHouseParserModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(WordTraversal.class, WordTraversalImpl.class)
                .build(WordTraversalFactory.class));
        
        bind(AddressFormatter.class).to(JsonAddressFormatter.class);
        bind(OutputWriter.class).to(ConsoleWriter.class);
        bind(InputReader.class).to(ConsoleReader.class);
        bind(WordExtractor.class).annotatedWith(Names.named("houseNumberExtractor")).to(HouseNumberExtractor.class);
        bind(WordExtractor.class).annotatedWith(Names.named("addressExtractor")).to(AddressExtractor.class);
    }
}
