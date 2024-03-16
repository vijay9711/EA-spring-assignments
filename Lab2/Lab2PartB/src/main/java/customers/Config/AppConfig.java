package customers.Config;

import customers.Component.EmailSender;
import customers.Component.EmailSenderImpl;
import customers.Component.Logger;
import customers.Component.LoggerImpl;
import customers.Repository.*;
import customers.Service.CustomerService;
import customers.Service.CustomerServiceImpl;
import customers.Service.ProductService;
import customers.Service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = {"customers.repository", "customers.service", "customers.component"})
public class AppConfig {

//    @Profile("test")
//    @Bean
//    public CustomerRepository customerRepository(){
//        return new CustomerRepositoryImpl();
//    }

    @Bean
    public CustomerService customerService(){
        return new CustomerServiceImpl();
    }

    @Bean
    public EmailSender emailSender(){
        return new EmailSenderImpl();
    }

//    @Bean
//    public Logger logger(){
//        return new LoggerImpl();
//    }

    @Bean
    public ProductService productService(){
        return new ProductServiceImpl();
    }


    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }
}
