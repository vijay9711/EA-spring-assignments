package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.OrderRepository;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	@Autowired
	OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Product product = new Book("Java 7", "hello java", 44.00, "784675647");
		OrderLine orderline1 = new OrderLine(4, product);

		Product product2 = new CD("Akon alone", "lonelt", 20.09, "akon");
		OrderLine orderline2 = new OrderLine(4, product2);

		Product product3 = new DVD("The meg", "hunger shark", 39.99, "Action");
		OrderLine orderline3 = new OrderLine(4, product3);

		Order order1 = new Order("234743", "12/10/06", "open");
		order1.addOrderLine(orderline1);
		order1.addOrderLine(orderline2);
		order1.addOrderLine(orderline3);

		Customer customer1 = new Customer("Frank", "Brown");
		customer1.addOrder(order1);
		order1.setCustomer(customer1);

		orderRepository.save(order1);


		Optional<Order> orderOpt = orderRepository.findById(1L);
		Order order = orderOpt.get();
		printOrder(order);
	}

	public static void printOrder(Order order) {
		System.out.println("Order number: " + order.getOrdernr());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstname() + " "
				+ cust.getLastname());
		for (OrderLine orderline : order.getOrderlines()) {
			System.out.println("Orderline: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice());
		}

	}
}
