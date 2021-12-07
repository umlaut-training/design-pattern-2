package com.umlaut.patterntraining.strategy;

import com.tngtech.valueprovider.ValueProvider;

import static com.tngtech.valueprovider.ValueProviderFactory.createRandomValueProvider;

public class ItemTestdatenErzeuger {
    private ItemTestdatenErzeuger() {
    }

    public static Item.ItemBuilder standardItemBuilder() {
        ValueProvider provider = createRandomValueProvider();
        return new Item.ItemBuilder()
                .withName(provider.fixedDecoratedString("Name"))
                .withPrice(provider.intNumber(1, 100))
                .withWeight(provider.intNumber(1, 100));
    }

    public static Item standardItem() {
        return standardItemBuilder().build();
    }
}
