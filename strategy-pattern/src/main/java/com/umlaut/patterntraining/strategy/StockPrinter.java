package com.umlaut.patterntraining.strategy;

import java.util.Map;

public class StockPrinter {

    private ItemPrinter itemPrinter = new ItemPrinter();

    public Iterable<Map.Entry<Item, Integer>> getEntries(Map<Item, Integer> input) {
        return input.entrySet();
    }

    public void print(Map<Item, Integer> stock) {
        if (stock == null)
            throw new IllegalArgumentException("The stock is not defined. Please provide a valid (=non-null) stock.");

        int index = 1;
        for (Map.Entry<Item, Integer> entry : getEntries(stock)) {
            itemPrinter.printItem(index, entry);
            index++;
        }
    }
}
