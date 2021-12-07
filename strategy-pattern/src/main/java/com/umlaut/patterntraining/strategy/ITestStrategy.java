package com.umlaut.patterntraining.strategy;

import java.util.Map;

public interface ITestStrategy {
    Iterable<Map.Entry<Item, Integer>> getEntries(Map<Item, Integer> input);
}
