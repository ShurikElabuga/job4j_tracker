package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    void whenAsc() {
        List<Item> itemList = Arrays.asList(
                new Item("Boris"),
                new Item("Alexandr"),
                new Item("Evgeniy"),
                new Item("Dmitriy")
        );
        List<Item> expected = Arrays.asList(
                new Item("Alexandr"),
                new Item("Boris"),
                new Item("Dmitriy"),
                new Item("Evgeniy")
        );
        itemList.sort(new ItemAscByName());
        assertThat(itemList.equals(expected));
    }

    @Test
    void whenDesc() {
        List<Item> itemList = Arrays.asList(
                new Item("Boris"),
                new Item("Alexandr"),
                new Item("Evgeniy"),
                new Item("Dmitriy")
        );
        List<Item> expected = Arrays.asList(
                new Item("Evgeniy"),
                new Item("Dmitriy"),
                new Item("Boris"),
                new Item("Alexandr")
        );
        itemList.sort(new ItemAscByName());
        assertThat(itemList.equals(expected));
    }
}