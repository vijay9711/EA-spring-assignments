package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.integration.EmailSender;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
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
	private EmailSender emailSender;

	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String AccountNumber){
		Account account = new Account(AccountNumber);
		accountRepository.save(account);
		Customer customer = new Customer(customerId, customerName);
        customer.setAccount(account);
		customerRepository.saveCustomer(customer);
		emailSender.sendEmail(emailAddress, "Welcome "+customerName);
	}
	public Iterable<Customer> getAllAccounts(){
		return customerRepository.findAll();
	}

}
