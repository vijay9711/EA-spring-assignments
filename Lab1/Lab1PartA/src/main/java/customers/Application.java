package customers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {

	public static void main(String[] args) {
		Logger logger = new LoggerImpl();
		CustomerRepository customerRepository = new CustomerRepositoryImpl(logger);
		EmailSender emailSender = new EmailSenderImpl(logger);
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"springconfig.xml");

		CustomerService customerService = context.getBean("customerService",
				CustomerService.class);
		customerService.serviceDependencySet(customerRepository, emailSender);
		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");

	}
}

