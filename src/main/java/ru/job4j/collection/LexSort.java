package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] str1 = o1.split("\\.");
        String[] str2 = o2.split("\\.");

        return Integer.compare(Integer.parseInt(str1[0]), Integer.parseInt(str2[0]));
    }
}
