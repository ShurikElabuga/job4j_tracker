package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] left = o1.split("/");
        String[] right = o2.split("/");
        int result = right[0].compareTo(left[0]);
        return result != 0 ? result : o1.compareTo(o2);
    }
}
