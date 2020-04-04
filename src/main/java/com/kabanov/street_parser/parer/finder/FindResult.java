package com.kabanov.street_parser.parer.finder;

public class FindResult {

    public int beginIndex;
    public int endIndex;

    public FindResult(int beginIndex, int endIndex) {
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    /**
     * @return – the beginning index, inclusive.
     */
    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    /**
     * @return endIndex – the ending index, exclusive.
     */
    public int getEndingIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
