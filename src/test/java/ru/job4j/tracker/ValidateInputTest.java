package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ValidateInputTest {

    @Test
    void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new MockInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    void whenValidInput() {
        Output out = new StubOutput();
        Input in = new MockInput(
                new String[] {"1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    void whenSeveralValidInput() {
        Output out = new StubOutput();
        Input in = new MockInput(
                new String[] {"0", "1", "2", "3", "4", "5", "6"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int[] selected = new int[7];
        for (int i = 0; i < 7; i++) {
            selected[i] = input.askInt("Enter menu:");
        }
        assertThat(Arrays.equals(selected, new int[]{0, 1, 2, 3, 4, 5, 6}));
    }

    @Test
    void whenMinesNumberInput() {
        Output out = new StubOutput();
        Input in = new MockInput(
                new String[] {"-1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(-1);
    }
}