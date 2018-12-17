package net.safedata.springboot.training.d01.s03.repository;

import net.safedata.spring.training.domain.bootstrap.ProductsSetup;
import net.safedata.spring.training.domain.model.Product;

import java.util.List;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
public class ProductRepository {

    private List<Product> products = ProductsSetup.getRandomProducts();

    public void displayProducts() {
        System.out.println("Displaying all the available products:");
        products.forEach(product -> System.out.println("\t" + product));
    }
}
