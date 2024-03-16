package customers.Repository;

import customers.Entity.Customer;
import customers.Component.Logger;
import customers.Component.LoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Profile("prod")
public class CustomerRepositoryImpl implements CustomerRepository {
	@Autowired
	Logger logger;

	public void save(Customer customer) {
		// simple sleep
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CustomerRepository: saving customer "+customer.getName() + " in prod env");
		logger.log("Customer is saved in the DB: "+ customer.getName() );
	}

}
