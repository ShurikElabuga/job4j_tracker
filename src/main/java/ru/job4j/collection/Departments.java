package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> fillGaps(List<String> departments) {
        List<String> result = new ArrayList<>();
        Set<String> temp = new LinkedHashSet<>();
        for (String value : departments) {
            String start = "";
            for (String element : value.split("/")) {
                start += "".equals(start) ? element : "/" + element;
                temp.add(start);
            }
        }
        result.addAll(temp);
        return result;
    }

    public static void sortAsc(List<String> departments) {
        Collections.sort(departments);
    }

    public static void sortDesc(List<String> departments) {
        departments.sort(new DepartmentsDescComparator());
    }
}
