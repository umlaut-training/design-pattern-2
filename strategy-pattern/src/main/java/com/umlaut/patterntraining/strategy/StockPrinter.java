package com.umlaut.patterntraining.strategy;

import java.util.Map;

public class StockPrinter {

    private ITestStrategy strategy = new DefaultTestStrategy();
    private ItemPrinter itemPrinter = new ItemPrinter();

    public void setStrategy(ITestStrategy strategy) {
        this.strategy = strategy;
    }

    public Iterable<Map.Entry<Item, Integer>> getEntries(Map<Item, Integer> input) {
        return strategy.getEntries(input);
        //return input.entrySet();
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
