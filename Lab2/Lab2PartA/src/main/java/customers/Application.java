package customers;

import customers.Config.AppConfig;
import customers.Service.CustomerService;
import customers.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"customers.repository", "customers.service", "customers.component"})
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//		ApplicationContext context = new AnnotationConfigApplicationContext();
//		ConfigurableEnvironment environment = (ConfigurableEnvironment) context.getEnvironment();
//		environment.setActiveProfiles("test");
//		System.out.println("active environment "+Arrays.toString(environment.getActiveProfiles()));
//		CustomerService customerService = context.getBean("customerService", CustomerService.class);
//		ProductService productService = context.getBean("productService", ProductService.class);
//		productService.addProduct("Spoon", 2324, "Kitchen", "fbrown@acme.com");
			customerService.addCustomer("Frank Brown", "fbrown@acme.com",
					"mainstreet 5", "Chicago", "60613");
	}
}

