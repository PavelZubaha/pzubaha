package pzubaha.collection.bank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Chapter_003. Collections. Lite.
 * Test task.
 * <p>
 * Contains solution of task 10038.
 * Class contains storage for bank.
 * Created 12.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Bank {

    /**
     * Storege for users accounts information.
     */
    private Map<User, List<Account>> usersAccaunts = new HashMap<User, List<Account>>(0, 75);

    /**
     * Adding user.
     * @param user user
     */
    public void addUser(User user) {
        usersAccaunts.put(user, new ArrayList<Account>());
    }

    /**
     * Deletion user.
     * @param user user.
     */
    public void deleteUser(User user) {
        usersAccaunts.remove(user);
    }

    /**
     * Add account to user.
     * @param user user.
     * @param account account.
     */
    public void addAccountToUser(User user, Account account) {
        List<Account> accounts = getUserAccounts(user);
        accounts.add(account);
    }

    /**
     * Delete account from user.
     * @param user user.
     * @param account account.
     */
    public void deleteAccountFromUser(User user, Account account) {
        List<Account> accounts = getUserAccounts(user);
        accounts.remove(account);
    }

    /**
     * получить список счетов для пользователя.
     * @param user user
     * @return list of user accounts.
     */
    public List<Account> getUserAccounts(User user) {
        List<Account> result;
        if (user != null) {
            result = usersAccaunts.get(user);
        } else {
            result = new ArrayList<Account>();
        }
        return  result;
    }

    /**
     * Method for transfer money from account to another.
     * If account doesn't exist or insufficient funds will be returned false.
     * @param srcUser source user.
     * @param srcAccount source account.
     * @param dstUser destination user.
     * @param dstAccount destination account.
     * @param amount amount of money
     * @return true if operation is valid, else return false.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = false;
        //transfer validation
        if (srcUser != null && dstUser != null
                && usersAccaunts.containsKey(srcUser)
                && getUserAccounts(srcUser).contains(srcAccount)
                && usersAccaunts.containsKey(dstUser)
                && getUserAccounts(dstUser).contains(dstAccount)
                && srcAccount.getBalance() >= amount) {
            //transfer
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            dstAccount.setBalance(dstAccount.getBalance() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Class account.
     */
    static class Account {
        /**
         * Account balance.
         */
        private double balance = 0;
        /**
         * Next account number.
         */
        private static long nextNumber = Long.MAX_VALUE;
        /**
         * Account number.
         */
        private final long number;
        /**
         * Month to which the account is valid.
         */
        private final int validThruMonth;
        /**
         * Year to which the account is valid.
         */
        private final int validThruYear;
        /**
         * Account holder first name.
         */
        private final String firstName;
        /**
         * Account holde last name.
         */
        private final String secondName;

        /**
         * Constructor for account.
         * @param validThruMonth month to which the account is valid.
         * @param validThruYear year to which the account is valid.
         * @param firstName first name of account holder.
         * @param lastName last name of account holder.
         * @param number account number.
         */
        private Account(int validThruMonth, int validThruYear, String firstName, String lastName, long number) {
            this.number = number;
            this.validThruMonth = validThruMonth;
            this.validThruYear = validThruYear;
            this.firstName = firstName;
            this.secondName = lastName;
        }
        /**
         * Getter for balance.
         * @return balance value.
         */
        public double getBalance() {
            return balance;
        }

        /**
         * Setter for balance value.
         * @param balance balance value.
         */
        public void setBalance(double balance) {
            this.balance = balance;
        }

        /**
         * Factory method for creating Account instance.
         * @param user account user.
         * @return reference to new Account instance.
         */
        public static Account makeAccount(User user) {
            Account result;
            if (user != null && user.getName() != null && user.getPassport() != null) {
                LocalDate validThruTo = LocalDate.now().plusYears(3);
                result = new Account(validThruTo.getMonthValue(), validThruTo.getYear(), user.getPassport().getFirstName(), user.getPassport().getLastName(), nextNumber--);
            } else {
                result = new Account(0, 0, "not valid name", "not valid name", 0);
            }
            return result;
        }
    }
}
