package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("davs@yandex.ru", "Denis Akimov");
        map.put("dodo@mail.ru", "Sergey Ivanov");
        map.put("hex@yandex.ru", "Anton Dudnik");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " = " + value);
        }
    }
}
