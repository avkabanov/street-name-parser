package com.kabanov.street_parser.parer.traversal;

import javax.annotation.Nonnull;

public class WordEntry {
    
    int index;
    String value;

    public WordEntry(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Nonnull
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
