package ru.job4j.tracker;

import ru.job4j.tracker.action.*;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.Validate;
import ru.job4j.tracker.output.Console;
import ru.job4j.tracker.output.Output;

import java.util.ArrayList;
import java.util.List;

public class StartUl {
    //private final Output out;

    public StartUl(Output out) {
        this.out = out;
    }

    public void init(Input input, Tracker tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Выбрать: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Неверный ввод, вы можете выбрать: 0 ... " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Меню: ");
        for (int i = 0; i < actions.size(); i++) {
            out.println(i + ". " + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Output output = new Console();
        Input input = new Validate(output, new ru.job4j.tracker.input.Console());
        Tracker tracker = new Tracker();
        List<UserAction> actions = new ArrayList<>();
                actions.add(new Create(output));
                actions.add(new FindAll(output));
                actions.add(new Replace(output));
                actions.add(new Delete(output));
                actions.add(new FindById(output));
                actions.add(new FindByName(output));
                actions.add(new Exit(output));
        new StartUl(output).init(input, tracker, actions);

    }
}
