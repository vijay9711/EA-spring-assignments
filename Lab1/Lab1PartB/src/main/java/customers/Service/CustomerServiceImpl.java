package customers.Service;

import customers.Entity.Address;
import customers.Entity.Customer;
import customers.Repository.CustomerRepository;
import customers.Component.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	private CustomerRepository customerRepository;
	private EmailSender emailSender;
	public void serviceDependencySet(CustomerRepository customerRepository, EmailSender emailSender) {
		this.customerRepository = customerRepository;
		this.emailSender = emailSender;
	}
//	CustomerRepository customerRepository = new CustomerRepositoryImpl();
//	EmailSender emailSender = new EmailSenderImpl();



	public void addCustomer(String name, String email, String street,
			String city, String zip) {
		Customer customer = new Customer(name, email);
		Address address = new Address(street, city, zip);
		customer.setAddress(address);
		customerRepository.save(customer);
		emailSender.sendEmail(email, "Welcome " + name + " as a new customer");
	}
}
