package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель клиента банка
 * со стандартами геттерами, сеттерами, конструктором
 * и переопределенными методами equals и hashCode
 */
public class User {
    /**
     * параметр дынных паспорта
     */
    private String passport;
    /**
     * параметр ФИО клиента
     */
    private String username;

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    public String getPassport() {
        return passport;
    }

    public String getUsername() {
        return username;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return passport.equals(user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
