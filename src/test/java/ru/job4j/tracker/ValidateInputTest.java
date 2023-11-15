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
        Input in = new MockInput(new String[] {"0", "1", "2"});
        ValidateInput input = new ValidateInput(out, in);
        int[] selected = new int[3];
        selected[0] = input.askInt("Enter menu:");
        assertThat(Arrays.equals(selected, new int[] {0}));
        selected[1] = input.askInt("Enter menu:");
        assertThat(Arrays.equals(selected, new int[] {1}));
        selected[2] = input.askInt("Enter menu:");
        assertThat(Arrays.equals(selected, new int[] {2}));
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