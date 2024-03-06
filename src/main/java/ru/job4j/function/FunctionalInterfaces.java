package ru.job4j.function;

import java.util.*;
import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        List<String> list = List.of("one", "two", "three", "four", "five", "six", "seven");
        BiConsumer<Integer, String> biConsumer = (first, second) -> map.put(first, second);
        int i = 1;
        for (String str : list) {
            biConsumer.accept(i++, str);
        }

        BiPredicate<Integer, String> biPredicate = (key, value) -> (key % 2 == 0 || value.length() == 4);
        for (Integer key : map.keySet()) {
            if (biPredicate.test(key, map.get(key))) {
                System.out.println("key: " + key + " value: " + map.get(key));
            }
        }
        Supplier<List<String>> supplier = () -> new ArrayList<>(map.values());
        List<String> strings = supplier.get();
        Consumer<String> consumer = (string) -> System.out.println(string);
        consumer.accept(strings.toString());
        Function<String, String> function = string -> string.toUpperCase();
        function.apply(strings.toString());
    }
}
