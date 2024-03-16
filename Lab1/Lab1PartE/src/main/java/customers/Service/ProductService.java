package customers.Service;

import customers.Component.EmailSender;
import customers.Repository.CustomerRepository;
import customers.Repository.ProductRepository;

public interface ProductService {
    void addProduct(String name, int itemNo, String Category, String email);
    void serviceDependencySet(ProductRepository productRepository, EmailSender emailSender);
}
