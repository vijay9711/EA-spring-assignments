package bank.service;
import bank.repository.AccountRepository;
import bank.domain.Account;
import bank.domain.Customer;
import bank.integration.jms.JMSSender;
//import bank.integration.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CurrencyConverter currencyConverter;

	@Autowired
	private JMSSender jmsSender;

	@Autowired
	private ApplicationEventPublisher publisher;

//	@Autowired
//	private Logger logger;

	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	public AccountResponse createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountRepository.save(account);
		logger.trace("createAccount with parameters accountNumber= "
				+ accountNumber + " , customerName= " + customerName);
		AccountResponse accountResponse = AccountAdapter.getAccountResponseFromAccount(account);
		return accountResponse;
	}

	public void deposit(long accountNumber, double amount) {
		Account account = accountRepository.findById(accountNumber).get();
		account.deposit(amount);
		accountRepository.save(account);
		logger.debug("deposit with parameters accountNumber= " + accountNumber
				+ " , amount= " + amount);
		if (amount > 10000) {
			jmsSender.sendJMSMessage("Deposit of $ " + amount
					+ " to account with accountNumber= " + accountNumber);
		}
		AccountResponse accountResponse = AccountAdapter.getAccountResponseFromAccount(account);
	}

	public AccountResponse getAccount(long accountNumber) {
		Account account = accountRepository.findById(accountNumber).get();
		return AccountAdapter.getAccountResponseFromAccount(account);
	}

	public Collection<AccountResponse> getAllAccounts() {
		List<Account> accountList = accountRepository.findAll();
		return AccountAdapter.getAccountResponseListFromAccountList(accountList);
	}

	public void withdraw(long accountNumber, double amount) {
		Account account = accountRepository.findById(accountNumber).get();
		account.withdraw(amount);
		accountRepository.save(account);
		logger.info("withdraw with parameters accountNumber= " + accountNumber
				+ " , amount= " + amount);
		AccountResponse accountResponse = AccountAdapter.getAccountResponseFromAccount(account);
	}

	public void depositEuros(long accountNumber, double amount) {
		Account account = accountRepository.findById(accountNumber).get();
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountRepository.save(account);
		logger.warn("depositEuros with parameters accountNumber= "
				+ accountNumber + " , amount= " + amount);
		if (amountDollars > 10000) {
			jmsSender.sendJMSMessage("Deposit of $ " + amount
					+ " to account with accountNumber= " + accountNumber);
		}
		AccountResponse accountResponse = AccountAdapter.getAccountResponseFromAccount(account);
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Account account = accountRepository.findById(accountNumber).get();
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountRepository.save(account);
		logger.error("withdrawEuros with parameters accountNumber= "
				+ accountNumber + " , amount= " + amount);
		AccountResponse accountResponse = AccountAdapter.getAccountResponseFromAccount(account);
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber,
							  double amount, String description) {
		Account fromAccount = accountRepository.findById(fromAccountNumber).get();
		Account toAccount = accountRepository.findById(toAccountNumber).get();
		fromAccount.transferFunds(toAccount, amount, description);
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		logger.trace("transferFunds with parameters fromAccountNumber= "
				+ fromAccountNumber + " , toAccountNumber= " + toAccountNumber
				+ " , amount= " + amount + " , description= " + description);
		if (amount > 10000) {
			jmsSender.sendJMSMessage("TransferFunds of $ " + amount
					+ " from account with accountNumber= " + fromAccount
					+ " to account with accountNumber= " + toAccount);
		}
		AccountResponse accountResponse = AccountAdapter.getAccountResponseFromAccount(fromAccount);
	}
}
