package com.umlaut.patterntraining.strategy;

import com.tngtech.valueprovider.ValueProvider;
import com.tngtech.valueprovider.ValueProviderFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class StockTestdatenErzeuger {
    private StockTestdatenErzeuger() {

    }

    public static Map<Item, Integer> standardStock(int size) {

        ValueProvider provider = ValueProviderFactory.createRandomValueProvider();
        Map<Item, Integer> stock = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            stock.put(ItemTestdatenErzeuger.standardItem(), provider.intNumber(0, 201));
        }
        return stock;
    }

    public static Map<Item, Integer> standardStock(Item... items) {

        ValueProvider provider = ValueProviderFactory.createRandomValueProvider();
        Map<Item, Integer> stock = new LinkedHashMap<>();
        for (Item item : items) {
            stock.put(item, provider.intNumber(0, 200));
        }
        return stock;
    }

}
