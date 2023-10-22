package ru.job4j.tracker;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StartUl {
    public static void main(String[] args) {
        Item item = new Item(900, "Tver");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        String currentDateTimeFormat = item.getCreated().format(formatter);
        System.out.println("Current date and time - " + currentDateTimeFormat);
        System.out.println(item);
    }
}
