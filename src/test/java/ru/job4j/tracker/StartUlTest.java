package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

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
        assertThat(tracker.findAll()[0].getName()).isEqualTo("Item name");
    }

    @Test
    void whenReplaceItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new MockInput(
                new String[] {"0", "1", "New item name", "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(out),
                new ExitAction(out)
        };
        new StartUl(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    void whenDeleteItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new MockInput(
                new String[] {"0", "1", "1"}
        );
        UserAction[] actions = {
                new DeleteAction(out),
                new ExitAction(out)
        };
        new StartUl(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
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
}