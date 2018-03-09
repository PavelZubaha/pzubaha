package pzubaha.threads.bank;

import java.util.ArrayList;
import java.util.List;


/**
 * Chapter_006. Multithreading.
 * 2.JMM.
 * Contains solution of task 1096
 * Illustration problems with multithreading task.
 * This class represents the Bank, which store some BankAccount's
 * The process is transfer random amount of money(0 - 100),
 * from random(0 - 100) from bank account to another random one(0 - 100).
 * Task run 2000 times by each of 100 threads.
 * When task runs, ShowThread showing how total balance of accounts change.
 * Created 16.02.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Bank {
    /**
     * List of accounts.
     */
    private final List<BankAccount> accounts = new ArrayList<>(100);

    /**
     * Fill accounts before illustration.
     */
    public void fillAccounts() {
        for (int i = 0; i != 100; i++) {
            accounts.add(new BankAccount(100));
        }
    }

    /**
     * Get account method.
     * @param i account number.
     * @return bank account.
     */
    public BankAccount getAccount(int i) {
        if (i >= accounts.size() && i < 0) {
            return null;
        }
        return accounts.get(i);
    }
    /**
     * Get total balance of bank accounts.
     * @return sum of bank account's current balances
     */
    public int getSum() {
        int result = 0;
        for (BankAccount ba : accounts) {
            result += ba.getBalance();
        }
        return result;
    }

    /**
     * Class for show total accounts sum, and print it out to console.
     */
    class ShowThread extends Thread {
        @Override
        public void run() {
            while (!this.isInterrupted()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("Show thread is interrupted while sleep");
                    break;
                }
                System.out.println("Sum of bank accounts: " + Bank.this.getSum());
            }
        }
    }

    /**
     * method for starting task, which is consists of parts:
     * - start of show thread.
     * - start of 100 transfer threads.
     * - print some info.
     * @return total balance of bank accounts.
     */
    public int process() {
        System.out.printf("Total balance of bank accounts is: %d%n", this.getSum());
        List<Thread> threads = new ArrayList<>(100);
        Thread showThread = new ShowThread();
        showThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i != 100; i++) {
            threads.add(new Thread(new RunnableTest(this)));
            threads.get(i).start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        showThread.interrupt();
        System.out.printf("Total balance of bank accounts is: %d%n", this.getSum());
        return this.getSum();
    }
}
