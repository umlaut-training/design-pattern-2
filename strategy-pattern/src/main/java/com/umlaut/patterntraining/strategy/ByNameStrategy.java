package com.umlaut.patterntraining.strategy;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class ByNameStrategy implements ITestStrategy {
    @Override
    public Iterable<Map.Entry<Item, Integer>> getEntries(Map<Item, Integer> input) {
        return input.entrySet().stream().sorted(
                Map.Entry.comparingByKey(Comparator.comparing(Item::getName))
        ).collect(Collectors.toList());
    }
}
