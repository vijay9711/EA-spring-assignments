package customers.Service;

import customers.Repository.CustomerRepository;
import customers.Component.EmailSender;
import org.springframework.stereotype.Service;


public interface CustomerService {

	void addCustomer(String name, String email, String street,String city, String zip);
//	void serviceDependencySet(CustomerRepository customerRepository);
}
