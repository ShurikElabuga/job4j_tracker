package ru.job4j.search;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

class PhoneDictionaryTest {

    @Test
    void whenFindByName() {
            var phones = new PhoneDictionary();
            phones.add(
                    new Person("Petr", "Arsentev", "534872", "Bryansk")
            );
            ArrayList<Person> persons = phones.find("Petr");
            assertThat(persons.get(0).getSurname()).isEqualTo("Arsentev");
        }

    @Test
    void whenNotFind() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("Pavel");
        assertThat(persons).isEmpty();
    }
}
