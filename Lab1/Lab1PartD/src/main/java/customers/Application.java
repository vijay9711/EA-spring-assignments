package customers;

import customers.Component.EmailSender;
import customers.Component.EmailSenderImpl;
import customers.Component.Logger;
import customers.Component.LoggerImpl;
import customers.Config.AppConfig;
import customers.Repository.CustomerRepository;
import customers.Repository.CustomerRepositoryImpl;
import customers.Service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {

	public static void main(String[] args) {
		Logger logger = new LoggerImpl();
		CustomerRepository customerRepository = new CustomerRepositoryImpl(logger);
		EmailSender emailSender = new EmailSenderImpl(logger);
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"springconfig.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		CustomerService customerService = context.getBean("customerService",
				CustomerService.class);
		customerService.serviceDependencySet(customerRepository, emailSender);
		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");

	}
}

