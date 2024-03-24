package ru.job4j.stream;

import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users;

    public BankService(Map<User, List<Account>> users) {
        this.users = users;
    }

    public User findByPassport(String passport) {
        /*for (User user : users.keySet()) {
            if (user.passport().equals(passport)) {
                return user;
            }
        }
        return null;*/
        return users.keySet()
                .stream()
                .filter(user -> user.passport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        /*if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account : accounts) {
                if (account.requisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }*/
        if (user == null) {
            return null;
        }
        return users.get(user)
                .stream()
                .filter(account -> account.requisite().equals(requisite))
                .findFirst()
                .orElse(null);
    }
}
