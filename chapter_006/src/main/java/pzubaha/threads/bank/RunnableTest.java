package pzubaha.threads.bank;

import java.util.Random;

/**
 * Chapter_006. Multithreading.
 * 2.JMM.
 * Contains solution of task 1096
 * Illustration problems with multithreading task.
 * Class represents task for threads.
 * Created 16.02.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
class RunnableTest implements Runnable {
    Bank bank;
    Random random = new Random();
    public RunnableTest(Bank b) {
        this.bank = b;
    }

    /**
     * Transfer random amount of money(0 - 100),
     * from random(0 - 100) bank account to another one.
     * Task run 2000 times by each of 100 threads.
     */
    @Override
    public void run() {
        int from, to, transfer;
        for (int j = 0; j != 2000; j++) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            transfer = random.nextInt(100);
            from = random.nextInt(100);
            if (bank.getAccount(from).withdraw(transfer)) {
                to = random.nextInt(100);
                bank.getAccount(to).deposit(transfer);
            }
        }
    }
}
