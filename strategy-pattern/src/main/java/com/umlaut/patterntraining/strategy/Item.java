package com.umlaut.patterntraining.strategy;

import java.util.Objects;

public class Item {

    private final String name;
    private final int price;
    private final int weight;

    public Item(ItemBuilder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.weight = builder.weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }


    public int getPrice() {
        return price;
    }

    public float getPricePerWeight() {
        return price / (float) weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getPrice() == item.getPrice() && getWeight() == item.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getWeight());
    }

    @Override
    public String toString() {
        return "Item{" + "name='" + this.getName() + '\'' +
                ", price=" + this.getPrice() +
                ", weight=" + this.getWeight() +
                ", pricePerWeight=" + this.getPricePerWeight() +
                '}';
    }

    public static class ItemBuilder {

        protected String name;
        protected int price;
        protected int weight;

        public ItemBuilder() {
        }

        public ItemBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder withPrice(int price) {
            this.price = price;
            return this;
        }

        public ItemBuilder withWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
