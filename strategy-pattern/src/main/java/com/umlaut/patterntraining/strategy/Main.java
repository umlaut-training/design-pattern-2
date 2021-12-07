package com.umlaut.patterntraining.strategy;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        runStrategyDemo();
    }

    public static void runStrategyDemo() {

        // Create Products
        Item apple = new Item.ItemBuilder().withName("Apple").withPrice(100).withWeight(150).build();
        Item pear = new Item.ItemBuilder().withName("Pear").withPrice(120).withWeight(205).build();
        Item egg = new Item.ItemBuilder().withName("Egg").withPrice(50).withWeight(60).build();
        Item strawberry = new Item.ItemBuilder().withName("Strawberry").withPrice(10).withWeight(12).build();

        // assemble product stock:
        Map<Item, Integer> stock = new HashMap<>();
        stock.put(apple, 12);
        stock.put(pear, 10);
        stock.put(egg, 36);
        stock.put(strawberry, 200);

        StockPrinter sp = new StockPrinter();

        System.out.println("Default order (Insertion Order):");
        sp.print(stock);

    }
}
