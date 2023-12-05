package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.Mock;
import ru.job4j.tracker.input.Validate;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.Stub;

import static org.assertj.core.api.Assertions.assertThat;

class ValidateTest {

    @Test
    void whenInvalidInput() {
        Output out = new Stub();
        Input in = new Mock(
                new String[] {"one", "1"}
        );
        Validate input = new Validate(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    void whenValidInput() {
        Output out = new Stub();
        Input in = new Mock(
                new String[] {"1"}
        );
        Validate input = new Validate(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    void whenSeveralValidInput() {
        Output out = new Stub();
        Input in = new Mock(new String[] {"0", "1", "2"});
        Validate input = new Validate(out, in);
        int[] selected = new int[3];
        selected[0] = input.askInt("Enter menu:");
        selected[1] = input.askInt("Enter menu:");
        selected[2] = input.askInt("Enter menu:");
        int[] expected = {0, 1, 2};
        assertThat(selected).isEqualTo(expected);
    }

    @Test
    void whenMinesNumberInput() {
        Output out = new Stub();
        Input in = new Mock(
                new String[] {"-1"}
        );
        Validate input = new Validate(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(-1);
    }
}