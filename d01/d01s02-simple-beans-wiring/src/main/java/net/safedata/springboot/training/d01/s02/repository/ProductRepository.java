package net.safedata.springboot.training.d01.s02.repository;

import net.safedata.spring.training.domain.bootstrap.ProductsSetup;
import net.safedata.spring.training.domain.model.Product;

import java.util.List;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
public class ProductRepository {

    // using an in-memory list of products, until we'll connect to a database
    private List<Product> products = ProductsSetup.getRandomProducts();

    public void displayProducts() {
        System.out.println("Displaying all the available products:");
        products.forEach(product -> System.out.println("\t" + product));
    }
}
