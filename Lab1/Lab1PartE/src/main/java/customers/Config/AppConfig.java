package customers.Config;

import customers.Service.CustomerService;
import customers.Service.CustomerServiceImpl;
import customers.Service.ProductService;
import customers.Service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig {
    @Bean
    public CustomerService customerService(){
        return new CustomerServiceImpl();
    }

    @Bean
    public ProductService productService(){
        return new ProductServiceImpl();
    }

}
