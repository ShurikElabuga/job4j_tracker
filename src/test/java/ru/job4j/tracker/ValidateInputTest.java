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
        Input in1 = new MockInput(new String[] {"0"});
        Input in2 = new MockInput(new String[] {"1"});
        Input in3 = new MockInput(new String[] {"2"});
        ValidateInput input1 = new ValidateInput(out, in1);
        int selected1 = input1.askInt("Enter menu:");
        ValidateInput input2 = new ValidateInput(out, in2);
        int selected2 = input2.askInt("Enter menu:");
        ValidateInput input3 = new ValidateInput(out, in3);
        int selected3 = input3.askInt("Enter menu:");
        assertThat(selected1).isEqualTo(0);
        assertThat(selected2).isEqualTo(1);
        assertThat(selected3).isEqualTo(2);
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