package pzubaha.collection.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Chapter_003. Collections. Lite.
 * Test task.
 * <p>
 * Contains solution of task 10038.
 * Test class for Bank.
 * Created 13.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class BankTest {
    /**
     * Test transfer money.
     * Case when not enough money.
     */
    @Test
    public void testTransferMoneyWhenInsufficientFunds() {
        //Create tested instance
        Bank bank = new Bank();
        User userSrc = new User(User.Passport.newPassport("Src", "User"));
        User userDst = new User(User.Passport.newPassport("Dst", "User"));

        //add users and accounts for transfer.
        bank.addUser(userSrc);
        Bank.Account accountSrc = Bank.Account.makeAccount(userSrc);

        bank.addUser(userDst);
        Bank.Account accountDst = Bank.Account.makeAccount(userDst);

        bank.addAccountToUser(userSrc, accountSrc);
        bank.addAccountToUser(userDst, accountDst);

        //set not enough money
        accountSrc.setBalance(10);

        //validation
        assertTrue(!bank.transferMoney(userSrc, accountSrc, userDst, accountDst, 100));
    }
    /**
     * Test transfer money.
     * Case when enough money.
     */
    @Test
    public void testTransferMoneyWhenEnoughMoney() {
        //Create tested instance
        Bank bank = new Bank();
        User userSrc = new User(User.Passport.newPassport("Src", "User"));
        User userDst = new User(User.Passport.newPassport("Dst", "User"));

        //add users and accounts for transfer.
        bank.addUser(userSrc);
        Bank.Account accountSrc = Bank.Account.makeAccount(userSrc);

        bank.addUser(userDst);
        Bank.Account accountDst = Bank.Account.makeAccount(userDst);

        bank.addAccountToUser(userSrc, accountSrc);
        bank.addAccountToUser(userDst, accountDst);

        //set enough money
        accountSrc.setBalance(1000);
        //validation
        assertTrue(bank.transferMoney(userSrc, accountSrc, userDst, accountDst, 100));
    }
    /**
     * Test transfer money.
     * Case when enough money.
     */
    @Test
    public void testTransferMoneyWhenEnoughMoneyCheckingSrcBalance() {
        //Create tested instance
        Bank bank = new Bank();
        User userSrc = new User(User.Passport.newPassport("Src", "User"));
        User userDst = new User(User.Passport.newPassport("Dst", "User"));

        //add users and accounts for transfer.
        bank.addUser(userSrc);
        Bank.Account accountSrc = Bank.Account.makeAccount(userSrc);

        bank.addUser(userDst);
        Bank.Account accountDst = Bank.Account.makeAccount(userDst);

        bank.addAccountToUser(userSrc, accountSrc);
        bank.addAccountToUser(userDst, accountDst);

        //set enough money
        accountSrc.setBalance(1000);

        //transfer
        bank.transferMoney(userSrc, accountSrc, userDst, accountDst, 100);
        double expected = 900;
        //validation
        assertThat(Double.toString(accountSrc.getBalance()), is(Double.toString(expected)));
    }
}
