package pzubaha.threads.bank;

import org.junit.Test;

/**
 * Chapter_006. Multithreading.
 * 2.JMM.
 * Contains solution of task 1096
 * Illustration problems with multithreading task.
 * Test class.
 * Created 16.02.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class BankTest {
    @Test
    public void whenProcessSomeTaskThenTotalBalanceOfBankWillChange() {
        Bank bank = new Bank();
        bank.fillAccounts();
        int afterBalance = bank.process();
        System.out.printf("Balance before process: %d%nBalance after process: %d", 10000, afterBalance);
    }
}