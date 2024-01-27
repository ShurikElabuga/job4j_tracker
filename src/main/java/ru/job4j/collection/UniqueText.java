package ru.job4j.collection;

import java.util.HashSet;

public class UniqueText {
    public boolean isEquals(String originText, String duplicateText) {
        boolean result = true;
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();
        for (String orgn : origin) {
            check.add(orgn);
        }
        for (String txt : text) {
            if (!check.contains(txt)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
