package com.umlaut.patterntraining.strategy;

import java.io.PrintStream;
import java.util.Map;

class ItemPrinter {

    void printItem(int index, Map.Entry<Item, Integer> item) {
        printItem(System.out, index, item);
    }

    @SuppressWarnings("SameParameterValue")
        // Visible for Testing
    void printItem(PrintStream stream, int index, Map.Entry<Item, Integer> item) {
        if (stream == null)
            throw new IllegalArgumentException("The output print stream is not defined. Please provide a valid (=non-null) output target");

        stream.printf("%d - %dx %s - Total Weight: %d%n", index, item.getValue(), item.getKey().toString(), item.getValue() * item.getKey().getWeight());
    }
}
