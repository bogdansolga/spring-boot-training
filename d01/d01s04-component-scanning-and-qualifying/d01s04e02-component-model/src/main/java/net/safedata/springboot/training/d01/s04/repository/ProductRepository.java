package net.safedata.springboot.training.d01.s04.repository;

import net.safedata.spring.training.domain.bootstrap.ProductsSetup;
import net.safedata.spring.training.domain.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
@Repository
public class ProductRepository {

    private List<Product> products = ProductsSetup.getRandomProducts();

    public void displayProducts() {
        System.out.println("Displaying all the available products:");
        products.forEach(product -> System.out.println("\t" + product));
    }
}
