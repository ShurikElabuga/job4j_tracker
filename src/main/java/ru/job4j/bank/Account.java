package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает счет клиента
 * со стандартными геттерами, сеттерами, конструктором
 * и переопределенными методами equals и hashCodе
 */
public class Account {
    /**
     * параметр реквизита счета
     */
    private String requisite;
    /**
     * параметр баланса счета
     */
    private double balance;

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    public String getRequisite() {
        return requisite;
    }

    public double getBalance() {
        return balance;
    }

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return requisite.equals(account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
