package customers;

import customers.Component.EmailSender;
import customers.Component.EmailSenderImpl;
import customers.Component.Logger;
import customers.Component.LoggerImpl;
import customers.Config.AppConfig;
import customers.Repository.CustomerRepository;
import customers.Repository.CustomerRepositoryImpl;
import customers.Repository.ProductRepository;
import customers.Repository.ProductRepositoryImpl;
import customers.Service.CustomerService;
import customers.Service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {

	public static void main(String[] args) {
//		dependencies for service
		Logger logger = new LoggerImpl();
		CustomerRepository customerRepository = new CustomerRepositoryImpl(logger);
		ProductRepository productRepository = new ProductRepositoryImpl(logger);
		EmailSender emailSender = new EmailSenderImpl(logger);
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"springconfig.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//		create service from bean
		CustomerService customerService = context.getBean("customerService",
				CustomerService.class);
		ProductService productService = context.getBean("productService", ProductService.class);

//		set dependency for service
		productService.serviceDependencySet(productRepository, emailSender);
		customerService.serviceDependencySet(customerRepository, emailSender);

		productService.addProduct("Spoon", 2324, "Kitchen", "fbrown@acme.com");
		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");

	}
}

