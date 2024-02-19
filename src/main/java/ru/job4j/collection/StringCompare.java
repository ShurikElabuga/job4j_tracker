package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int length1 = o1.length();
        int length2 = o2.length();
        int rsl1;
        int rsl2 = Integer.compare(length1, length2);
        int length = rsl2 >= 0 ? length2 : length1;
        for (int i = 0; i < length; i++) {
            rsl1 = Character.compare(o1.charAt(i), o2.charAt(i));
            if (rsl1 != 0) {
                return rsl1;
            }
        }
        return rsl2;
    }
}
