package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class StartUlTest {

    @Test
    void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new MockInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitAction(out)
        };
        new StartUl(out).init(in, tracker, actions);
        String one = Arrays.toString(tracker.findAll()).replace("[", "").replace("]", "");
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Меню: " + ln
                        + "0. Добавить новую заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Создание новой заявки ===" + ln
                        + "Добавленная заявка: " + one + ln
                        + "Меню: " + ln
                        + "0. Добавить новую заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new MockInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(out),
                new ExitAction(out)
        };
        new StartUl(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Меню: " + ln
                        + "0. Заменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Редактирование заявки ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Меню: " + ln
                        + "0. Заменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindAllActionIsSuccessfully() {
    Output out = new StubOutput();
    Tracker tracker = new Tracker();
    Item one = tracker.add(new Item("test1"));
    Item two = tracker.add(new Item("test2"));
    Input in = new MockInput(
            new String[] {"0", "1"}
    );
    UserAction[] actions = new UserAction[] {
            new FindAllAction(out),
            new ExitAction(out)
    };
    new StartUl(out).init(in, tracker, actions);
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
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        Input in = new MockInput(
                new String[] {"0", one.getName(), "1"}
        );
        UserAction[] actions = new UserAction[] {
                new FindByNameAction(out),
                new ExitAction(out)
        };
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
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        Input in = new MockInput(
                new String[] {"0", String.valueOf(one.getId()), "1"}
        );
        UserAction[] actions = new UserAction[] {
                new FindByIdAction(out),
                new ExitAction(out)
        };
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
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new MockInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(out),
                new ExitAction(out)
        };
        new StartUl(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Меню: " + ln
                        + "0. Удалить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Удаление заявки ===" + ln
                        + "Заявка удалена успешно." + ln
                        + "Меню: " + ln
                        + "0. Удалить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

   @Test
   void whenExit() {
       Output out = new StubOutput();
       Input in = new MockInput(
               new String[] {"0"}
       );
       Tracker tracker = new Tracker();
       UserAction[] actions = {
               new ExitAction(out)
       };
       new StartUl(out).init(in, tracker, actions);
       assertThat(out.toString()).isEqualTo(
               "Меню: " + System.lineSeparator()
                       + "0. Завершить программу" + System.lineSeparator()
                       + "=== Завершение программы ===" + System.lineSeparator()
       );
   }

    @Test
    void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new MockInput(
                new String[] {"1", "0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = new UserAction[]{
                new ExitAction(out)
        };
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