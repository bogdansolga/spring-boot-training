package net.safedata.springboot.training.d01.s03.repository;

import net.safedata.spring.training.domain.bootstrap.ProductsSetup;
import net.safedata.spring.training.domain.model.Product;

import jakarta.annotation.PostConstruct;
import java.util.List;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
public class ProductRepository {

    private final List<Product> products = ProductsSetup.getRandomProducts();

    @PostConstruct
    public void init() {
        System.out.println("Initializing the repository, using the PostConstruct annotation...");
    }

    public void displayProducts() {
        System.out.println("Displaying all the available products:");
        products.forEach(product -> System.out.println("\t" + product));
    }
}
