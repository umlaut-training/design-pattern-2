package com.umlaut.patterntraining.strategy;

import org.assertj.core.data.MapEntry;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.umlaut.patterntraining.strategy.ItemTestdatenErzeuger.standardItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ItemPrinterTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private ItemPrinter itemPrinter = new ItemPrinter();

    @Test
    public void inputOfNull_results_in_exception() {

        assertThatThrownBy(() -> itemPrinter.printItem(null, 0, MapEntry.entry(standardItem(), 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("output print stream is not defined");
    }

    @Test
    public void item_wird_korrekt_ausgegeben() {
        // given
        final Item apple = new Item.ItemBuilder().withName("Apple").withPrice(100).withWeight(150).build();
        MapEntry<Item, Integer> item = MapEntry.entry(apple, 12);
        int index = 7;
        String expected = "7 - 12x Item{name='Apple', price=100, weight=150, pricePerWeight=0.6666667} - Total Weight: 1800\r\n";

        // when
        itemPrinter.printItem(new PrintStream(outputStreamCaptor), index, item);

        // then
        String output = outputStreamCaptor.toString();
        assertThat(output).isEqualTo(expected);
    }
}