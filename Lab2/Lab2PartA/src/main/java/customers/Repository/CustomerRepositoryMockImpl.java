package customers.Repository;

import customers.Component.Logger;
import customers.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component
@Profile("test")
public class CustomerRepositoryMockImpl implements CustomerRepository {
    @Autowired
     Logger logger;
//    @Autowired
//    public CustomerRepositoryMockImpl(Logger logger){
//        this.logger = logger;
//    }

    public void save(Customer customer) {
        // simple sleep
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CustomerRepository: saving customer "+customer.getName() + " in test env");
        logger.log("Customer is saved in the DB: "+ customer.getName() );
    }
}
