package com.kabanov.street_parser.parer.traversal;

import java.util.Objects;

import javax.annotation.Nonnull;

public class WordEntry {
    
    private int index;
    private String value;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordEntry wordEntry = (WordEntry) o;
        return index == wordEntry.index &&
                Objects.equals(value, wordEntry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, value);
    }

    @Override
    public String toString() {
        return "WordEntry{" +
                "index=" + index +
                ", value='" + value + '\'' +
                '}';
    }
}


