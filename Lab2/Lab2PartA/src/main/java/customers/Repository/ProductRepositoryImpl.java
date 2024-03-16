package customers.Repository;

import customers.Component.Logger;
import customers.Component.LoggerImpl;
import customers.Entity.Customer;
import customers.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
        @Autowired
        Logger logger;
//    @Autowired
//    public ProductRepositoryImpl(Logger logger){
//        this.logger = logger;
//    }

    public void save(Product product) {
        // simple sleep
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ProductRepository: saving product "+product.getName());
        logger.log("Product is saved in the DB: "+ product.getName() );
    }
}
