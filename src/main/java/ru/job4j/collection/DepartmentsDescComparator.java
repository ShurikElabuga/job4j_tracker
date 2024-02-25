package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] left = o1.split("/");
        String[] right = o2.split("/");
        if (left[0].equals(right[0])) {
            for (int i = 1; i < Math.min(left.length, right.length); i++) {
                if (!left[i].equals(right[i])) {
                    return left[i].compareTo(right[i]);
                }
            }
        } else {
            return right[0].compareTo(left[0]);
        }
        return Integer.compare(left.length, right.length);
    }
}
