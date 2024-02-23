package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс представляет сервис по обслуживанию клиентов и их счетов
 */
public class BankService {
    /**
     * Параметр содержащий базу данных клиентов и их счетов
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * метод принимает на вход клиента и добавляет его в базу данных банка
     * добавление происходит после проверки на отсутствие клиента в списке
     * @param user клиент, который добавляется в список
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * метод принимает данные паспорта и удаляет из базы данных клиента с этими данными
     * @param passport данные паспорта, по которым происходит поиск клиента
     */
    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    /**
     * метод принимает данные паспорта клиента и его счет
     * производит поиск клиента по данным паспорта
     * добавляет в список счетов клиента новый счет, проверив, что он новый
     * @param passport данные паспорта, по которым происходит поиск клиента
     * @param account счет, который добавляется в списко счетов клиента
     */
    public void addAccount(String passport, Account account) {
        User cons = findByPassport(passport);
        if (cons != null) {
            List<Account> ac = users.get(cons);
            if (!ac.contains(account)) {
                ac.add(account);
            }
        }
    }

    /**
     * метод принимает данные паспорта и производит поиск клиента по этим данным
     * @param passport данные паспорта по которым происходит поиск клиента
     * @return возвращает найденного клиента или null, если его нет в списке
     */
    public User findByPassport(String passport) {
        for (User psp : users.keySet()) {
            if (psp.getPassport().equals(passport)) {
                return psp;
            }
        }
        return null;
    }

    /**
     * метод производит поиск клиента по данным паспорта
     * а потом находит счет этого клиента по полученным реквизитам
     * @param passport данные паспорта, по которым производится поиск клиента в базе данных
     * @param requisite реквизиты, по которым производится поиск счета в списке счетов клиента
     * @return
     */
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

    /**
     * меотод получает данные паспорта клиента и реквизитов счета клиента,
     * с которого необходимо списать указанную сумму
     * и данные паспорта клиента и реквизитов счета клиента,
     * на который надо внести указанную сумму
      * @param sourcePassport паспорт клиента, со счета которого списывается сумма
     * @param sourceRequisite реквизиты счета, с которого списывается сумма
     * @param destinationPassport паспорт клиента, на который поступает сумма
     * @param destinationRequisite реквизаты счета, на который поступает сумма
     * @param amount сумма
     * @return возвращает true в случае успешного перечисления суммы со счета на счет
     * и false - в случае неудачи операции по переводу (не найден клиент или счет, отсутствует
     * необходимая сумма на счете - источнике)
     */
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
