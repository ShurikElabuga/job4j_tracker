package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringCompareTest {

    @Test
    public void whenStringsAreEqualThenZero() {
        StringCompare compare = new StringCompare();
        int result = compare.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int result = compare.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(result).isLessThan(0);
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        StringCompare compare = new StringCompare();
        int result = compare.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(result).isGreaterThan(0);
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        StringCompare compare = new StringCompare();
        int result = compare.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(result).isGreaterThan(0);
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int result = compare.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(result).isLessThan(0);
    }

    @Test
    public void secondCharOfLeftLessThanRightNumber() {
        StringCompare compare = new StringCompare();
        int result = compare.compare(
                "Patrov1",
                "Petrov2"
        );
        assertThat(result).isLessThan(0);
    }

    @Test
    public void secondCharOfLeftLessThanRight() {
        StringCompare compare = new StringCompare();
        int result = compare.compare(
                "2 Petrov",
                "Petrova"
        );
        assertThat(result).isLessThan(0);
    }

    @Test
    public void secondCharOfLeftGreaterThanRight() {
        StringCompare compare = new StringCompare();
        int result = compare.compare(
                "5Petrov",
                " 1Petrov!"
        );
        assertThat(result).isGreaterThan(0);
    }

    @Test
    public void whenStringsAreEqual() {
        StringCompare compare = new StringCompare();
        int result = compare.compare(
                "",
                ""
        );
        assertThat(result).isEqualTo(0);
    }
}