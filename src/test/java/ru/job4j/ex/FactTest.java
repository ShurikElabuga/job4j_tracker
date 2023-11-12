package ru.job4j.ex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactTest {

    @Test
    void whenException() {
        Fact fact = new Fact();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    fact.calc(-1);
                });
        assertThat(exception.getMessage()).isEqualTo("N could not be less than 0");
    }

    @Test
    void whenNotException() {
        Fact fact = new Fact();
        int n = 4;
        int expected = 24;
        int rsl = fact.calc(n);
        assertThat(rsl).isEqualTo(expected);
    }
}