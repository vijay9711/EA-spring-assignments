package bank.service;

import bank.AccountCreationException;
import bank.domain.Account;
import bank.domain.Customer;
import bank.domain.TraceRecord;
import bank.integration.EmailSender;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import bank.repository.TraceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TraceRecordRepository traceRecordRepository;

	@Autowired
	private EmailSender emailSender;

	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String accountNumber) {
		Account account = new Account(accountNumber);
		accountRepository.save(account);

		Customer customer = new Customer(customerId, customerName);
		customer.setAccount(account);

		try {
			customerRepository.saveCustomer(customer);
			emailSender.sendEmail(emailAddress, "Welcome " + customerName);
			saveTraceRecord("Customer " + customerName + " created with account " + accountNumber);
		} catch (AccountCreationException e) {
			emailSender.sendEmail(emailAddress, e.getMessage());
			saveTraceRecord("Could not create customer " + customerName + " with account " + accountNumber);
		}
	}

	private void saveTraceRecord(String result) {
		TraceRecord traceRecord = new TraceRecord(result);
		traceRecordRepository.save(traceRecord);
	}

	public Iterable<Customer> getAllAccounts() {
		return customerRepository.findAll();
	}
}
