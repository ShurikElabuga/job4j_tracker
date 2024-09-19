package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.*;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.Mock;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.Stub;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StartUlTest {

    @Test
    void whenCreateItem() {
        Output out = new Stub();
        Input in = new Mock(
                new String[] {"0", "Item name", "1"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new Create(out),
                new Exit(out)
        );
        new StartUl(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName().contains(new Item("Item name").getName()));
    }

    @Test
    void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new Stub();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new Mock(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new Replace(out),
                new Exit(out)
        );
        new StartUl(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    void whenFindAllActionIsSuccessfully() {
    Output out = new Stub();
    MemTracker tracker = new MemTracker();
    Item one = tracker.add(new Item("test1"));
    Item two = tracker.add(new Item("test2"));
    Input in = new Mock(
            new String[] {"0", "1"}
    );
    UserAction[] actions = new UserAction[] {
            new FindAll(out),
            new Exit(out)
    };
    new StartUl(out).init(in, tracker, List.of(actions));
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Меню: " + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод всех заявок ===" + ln
                        + one + ln
                        + two + ln
                        + "Меню: " + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByNameActionIsSuccessfully() {
        Output out = new Stub();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        Input in = new Mock(
                new String[] {"0", String.valueOf(one.getName()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindByName(out),
                new Exit(out)
        );
        new StartUl(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Меню: " + ln
                        + "0. Показать заявку по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявок по имени ===" + ln
                        + one + ln
                        + "Меню: " + ln
                        + "0. Показать заявку по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByIdActionIsSuccessfully() {
        Output out = new Stub();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        Input in = new Mock(
                new String[] {"0", String.valueOf(one.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindById(out),
                new Exit(out)
        );
        new StartUl(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Меню: " + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявки по id ===" + ln
                        + one + ln
                        + "Меню: " + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenDeleteItem() {
        Output out = new Stub();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new Mock(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new Delete(out),
                new Exit(out)
        );
        new StartUl(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
    }

   @Test
   void whenExit() {
       Output out = new Stub();
       Input in = new Mock(
               new String[] {"0"}
       );
       MemTracker tracker = new MemTracker();
       List<UserAction> actions = List.of(
               new Exit(out)
       );
       new StartUl(out).init(in, tracker, actions);
       String ln = System.lineSeparator();
       assertThat(out.toString()).isEqualTo(
               "Меню: " + ln
                       + "0. Завершить программу" + ln
                       + "=== Завершение программы ===" + ln
       );
   }

    @Test
    void whenInvalidExit() {
        Output out = new Stub();
        Input in = new Mock(
                new String[] {"1", "0"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(
                new Exit(out)
        );
        new StartUl(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Меню: " + ln
                        + "0. Завершить программу" + ln
                        + "Неверный ввод, вы можете выбрать: 0 ... 0" + ln
                        + "Меню: " + ln
                        + "0. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }
}
