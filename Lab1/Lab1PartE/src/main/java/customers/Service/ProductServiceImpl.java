package customers.Service;

import customers.Component.EmailSender;
import customers.Entity.Product;
import customers.Repository.CustomerRepository;
import customers.Repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements  ProductService{
    private ProductRepository productRepository;
    private EmailSender emailSender;
    @Override
    public void serviceDependencySet(ProductRepository productRepository,EmailSender emailSender) {
        this.emailSender = emailSender;
        this.productRepository = productRepository;
    }
    @Override
    public void addProduct(String name, int itemNo, String category, String email) {
        Product product = new Product(name, itemNo, category);
        productRepository.save(product);
        emailSender.sendEmail(email, "New product " + name + " added to category " + category);
    }

}
