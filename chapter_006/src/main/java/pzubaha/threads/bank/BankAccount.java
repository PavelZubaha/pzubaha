package pzubaha.threads.bank;

/**
 * Chapter_006. Multithreading.
 * 2.JMM.
 * Contains solution of task 1096
 * Illustration problems with multithreading task.
 * Class represents bank account.
 * Created 16.02.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}
