import bank.domain.Account;
import bank.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;

public class AccountTest {
    @Test
    public void testIncrement() {
        Account account = new Account();
        account.deposit(100.0);
        assertThat(account.getBalance(), closeTo(100.0, 0.01));
    }

    @Test
    public void testInitialBalanceIsZero() {
        Account account = new Account();
        assertThat(account.getBalance(), closeTo(0.0, 0.01));
    }

    @Test
    public void testDepositIncreasesBalance() {
        Account account = new Account();
        account.deposit(100.0);
        assertThat(account.getBalance(), closeTo(100.0, 0.01));
    }

    @Test
    public void testWithdrawDecreasesBalance() {
        Account account = new Account();
        account.deposit(100.0);
        account.withdraw(50.0);
        assertThat(account.getBalance(), closeTo(50.0, 0.01));
    }

    @Test
    public void testWithdrawMoreThanBalance() {
        Account account = new Account();
        account.deposit(50.0);
        account.withdraw(100.0);
        assertThat(account.getBalance(), closeTo(50.0, 0.01));
    }

    @Test
    public void testDepositNegativeAmount() {
        Account account = new Account();
        account.deposit(-50.0);
        assertThat(account.getBalance(), closeTo(0.0, 0.01));
    }

    @Test
    public void testWithdrawNegativeAmount() {
        Account account = new Account();
        account.deposit(100.0);
        account.withdraw(-50.0);
        assertThat(account.getBalance(), closeTo(100.0, 0.01));
    }

    @Test
    public void testTransferFundsToSameAccount() {
        Account account = new Account();
        account.deposit(100.0);
        account.transferFunds(account, 50.0, "Transfer to same account");
        assertThat(account.getBalance(), closeTo(100.0, 0.01));
    }

    @Test
    public void testTransferFundsIncreasesTargetAccountBalance() {
        Account sourceAccount = new Account();
        Account targetAccount = new Account();
        sourceAccount.setCustomer(new Customer("Source Customer"));
        targetAccount.setCustomer(new Customer("Target Customer"));
        sourceAccount.deposit(100.0);
        sourceAccount.transferFunds(targetAccount, 50.0, "Transfer to another account");
        assertThat(targetAccount.getBalance(), closeTo(50.0, 0.01));
    }

    @Test
    public void testTransferFundsDecreasesSourceAccountBalance() {
        Account sourceAccount = new Account();
        Account targetAccount = new Account();
        sourceAccount.setCustomer(new Customer("Source Customer"));
        targetAccount.setCustomer(new Customer("Target Customer"));
        sourceAccount.deposit(100.0);
        sourceAccount.transferFunds(targetAccount, 50.0, "Transfer to another account");
        assertThat(sourceAccount.getBalance(), closeTo(50.0, 0.01));
    }

    @Test
    public void testTransferFundsToNonExistingAccount() {
        Account sourceAccount = new Account();
        Account targetAccount = new Account();
        sourceAccount.setCustomer(new Customer("Source Customer"));
        targetAccount.setCustomer(new Customer("Target Customer"));
        sourceAccount.deposit(100.0);
        sourceAccount.transferFunds(targetAccount, 50.0, "Transfer to non-existing account");
        assertThat(targetAccount.getBalance(), closeTo(0.0, 0.01));
    }
}
