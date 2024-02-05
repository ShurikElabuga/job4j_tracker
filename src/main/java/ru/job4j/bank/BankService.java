package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    public void addAccount(String passport, Account account) {
        User cons = findByPassport(passport);
        if (cons != null) {
            List<Account> ac = users.get(cons);
            if (!ac.contains(account)) {
                ac.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        for (User psp : users.keySet()) {
            if (psp.getPassport().equals(passport)) {
                return psp;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account rsl = null;
        User cons = findByPassport(passport);
        if (cons != null) {
            List<Account> list = users.get(cons);
            for (Account ac : list) {
                if (requisite.equals(ac.getRequisite())) {
                    rsl = ac;
                    break;
                }
            }
        }
        return rsl;
    }

    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        boolean result = false;
        Account accountFrom = findByRequisite(sourcePassport, sourceRequisite);
        Account accountIn = findByRequisite(destinationPassport, destinationRequisite);
        if (accountFrom != null && accountIn != null) {
            double balanceFrom = accountFrom.getBalance();
            double balanceIn = accountIn.getBalance();
            if (balanceFrom >= amount) {
                balanceFrom -= amount;
                balanceIn += amount;
                result = true;
                accountFrom.setBalance(balanceFrom);
                accountIn.setBalance(balanceIn);
            }
        }
        return result;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
