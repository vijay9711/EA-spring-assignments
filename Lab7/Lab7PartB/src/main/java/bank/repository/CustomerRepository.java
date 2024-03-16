package bank.repository;

import bank.AccountCreationException;
import bank.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    default void saveCustomer(Customer customer) {
        try {
            save(customer);
//            throw new RuntimeException("could not save customer");
        } catch (Exception e) {
            throw new AccountCreationException("Could not create your account " + customer.getAccount().getAccountNumber());
        }
    }
}
