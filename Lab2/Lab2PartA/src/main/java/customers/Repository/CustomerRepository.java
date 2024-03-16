package customers.Repository;

import customers.Entity.Customer;
import org.springframework.stereotype.Repository;


public interface CustomerRepository {

	void save(Customer customer);

}
