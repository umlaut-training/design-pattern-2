package com.umlaut.patterntraining.strategy;

import java.util.Map;

public class DefaultTestStrategy implements ITestStrategy {
    @Override
    public Iterable<Map.Entry<Item, Integer>> getEntries(Map<Item, Integer> input) {
        return input.entrySet();
    }
}
