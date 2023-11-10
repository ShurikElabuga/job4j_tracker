package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StartUlTest {

    @Test
    void whenCreateItem() {
        Input in = new MockInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(),
                new ExitAction()
        };
        new StartUl().init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName()).isEqualTo("Item name");
    }

    @Test
    void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new MockInput(
                new String[] {"0", "1", "New item name", "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(),
                new ExitAction()
        };
        new StartUl().init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new MockInput(
                new String[] {"0", "1", "1"}
        );
        UserAction[] actions = {
                new DeleteAction(),
                new ExitAction()
        };
        new StartUl().init(in, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
    }
}