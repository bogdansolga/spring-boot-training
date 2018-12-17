package net.safedata.springboot.training.d01.s02.repository;

import net.safedata.spring.training.domain.model.Discount;
import net.safedata.spring.training.domain.model.Product;

import java.util.Arrays;
import java.util.List;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
public class ProductRepository {

    // using an in-memory list of products, until we'll connect to a database
    private List<Product> products = Arrays.asList(
            new Product(1, "Google Nexus 7", 200, new Discount(50, Discount.Type.Value)),
            new Product(2, "Apple iPad Pro", 300, new Discount(10, Discount.Type.Percent)),
            new Product(3, "Samsung Galaxy Tab", 350)
    );

    public void displayProducts() {
        System.out.println("Displaying all the available products:");
        products.forEach(product -> System.out.println("\t" + product));
    }
}
