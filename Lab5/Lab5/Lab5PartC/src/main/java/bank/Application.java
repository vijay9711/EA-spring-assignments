package bank;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.dto.AccountDTO;
import bank.dto.AccountEntryDTO;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// create 2 accounts;
		AccountDTO account1 = accountService.createAccount(1263862, "Frank Brown");
		AccountDTO account2 = accountService.createAccount(4253892, "John Doe");

		// use account 1;
		accountService.deposit(account1.getAccountNumber(), 240);
		accountService.deposit(account1.getAccountNumber(), 529);
		accountService.withdrawEuros(account1.getAccountNumber(), 230);

		// use account 2;
		accountService.deposit(account2.getAccountNumber(), 12450);
		accountService.depositEuros(account2.getAccountNumber(), 200);
		accountService.transferFunds(account2.getAccountNumber(), account1.getAccountNumber(), 100, "payment of invoice 10232");

		// show balances
		Collection<AccountDTO> accountList = accountService.getAllAccounts();
		for (AccountDTO account : accountList) {
			System.out.println("Statement for Account: " + account.getAccountNumber());
			System.out.println("Account Holder: " + account.getCustomerName());
			System.out.println(
					"-Date-------------------------" + "-Description------------------" + "-Amount-------------");
			for (AccountEntryDTO entry : account.getEntryList()) {
				System.out.printf("%30s%30s%20.2f\n", entry.getDate().toString(), entry.getDescription(),
						entry.getAmount());
			}
			System.out.println("----------------------------------------" + "----------------------------------------");
			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
		}
	}
}
