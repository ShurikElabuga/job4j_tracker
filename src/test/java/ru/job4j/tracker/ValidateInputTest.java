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
        Input in = new MockInput(new String[] {"0"});
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(0);
        in = new MockInput(new String[] {"1"});
        input = new ValidateInput(out, in);
        selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
        in = new MockInput(new String[] {"2"});
        input = new ValidateInput(out, in);
        selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(2);
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