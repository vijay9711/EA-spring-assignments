package bank.service;

import bank.domain.*;
import bank.dto.AccountDTO;
import bank.dto.AccountEntryDTO;
import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CurrencyConverter currencyConverter;
	@Autowired
	private JMSSender jmsSender;
	@Autowired
	private Logger logger;

	@Override
	public AccountDTO createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= " + accountNumber + " , customerName= " + customerName);
		return mapToDTO(account);
	}

	@Override
	public void deposit(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		account.deposit(amount);
		accountRepository.save(account);
		logger.log("deposit with parameters accountNumber= " + accountNumber + " , amount= " + amount);
		if (amount > 10000) {
			jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
		}
	}

	@Override
	public AccountDTO getAccount(long accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		return mapToDTO(account);
	}

	@Override
	public Collection<AccountDTO> getAllAccounts() {
		return accountRepository.findAll().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void withdraw(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		account.withdraw(amount);
		accountRepository.save(account);
		logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
	}

	@Override
	public void depositEuros(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountRepository.save(account);
		logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
		if (amountDollars > 10000) {
			jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
		}
	}

	@Override
	public void withdrawEuros(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountRepository.save(account);
		logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
	}

	@Override
	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
		Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber +
				" , toAccountNumber= " + toAccountNumber + " , amount= " + amount + " , description= " + description);
		if (amount > 10000) {
			jmsSender.sendJMSMessage("TransferFunds of $ " + amount + " from account with accountNumber= " + fromAccount +
					" to account with accountNumber= " + toAccount);
		}
	}

	private AccountDTO mapToDTO(Account account) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountNumber(account.getAccountNumber());
		accountDTO.setBalance(account.getBalance());
		accountDTO.setCustomerName(account.getCustomer().getName());
		accountDTO.setEntryList(account.getEntryList().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList()));
		return accountDTO;
	}

	private AccountEntryDTO mapToDTO(AccountEntry entry) {
		AccountEntryDTO entryDTO = new AccountEntryDTO();
		entryDTO.setAmount(entry.getAmount());
		entryDTO.setDescription(entry.getDescription());
		entryDTO.setFromAccountNumber(entry.getFromAccountNumber());
		entryDTO.setFromPersonName(entry.getFromPersonName());
		return entryDTO;
	}
}
