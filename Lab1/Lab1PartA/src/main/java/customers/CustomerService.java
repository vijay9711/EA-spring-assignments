package customers;

public interface CustomerService {

	void addCustomer(String name, String email, String street,String city, String zip);
	void serviceDependencySet(CustomerRepository customerRepository, EmailSender emailSender);
}
