package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tracker {
    List<Item> items = new ArrayList<>();
    private int ids = 1;

    public List<Item> add(Item item) {
        item.setId(ids++);
        items.add(item);
        return List.copyOf(items);
    }

    public List<Item> findAll() {
        return List.copyOf(items);
    }

    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();
        for (Item nm : items) {
            if (key.equals(nm.getName())) {
                rsl.add(nm);
            }
        }
        return List.copyOf(rsl);
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (Item it : items) {
            if (it.getId() == id) {
                rsl = it.getId();
                break;
            }
        }
        return rsl;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        item.setId(id);
        boolean rsl = index != -1;
        if (rsl) {
            items.set(index, item);
        }
        return rsl;
    }

    public void delete(int id) {
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
        }
    }
}