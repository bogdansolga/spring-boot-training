package net.safedata.springboot.training.d01.s04.repository;

import net.safedata.spring.training.domain.bootstrap.ProductsSetup;
import net.safedata.spring.training.domain.model.Product;

import java.util.List;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
public class ProductRepository {

    public static final String MY_SQL_REPO_BEAN_NAME = "mySQLProductRepository";

    public static final String ORACLE_REPO_BEAN_NAME = "oracleProductRepository";

    private final List<Product> products = ProductsSetup.getRandomProducts();

    private final String databaseType;

    public ProductRepository(final String databaseType) {
        this.databaseType = databaseType;
    }

    public void displayProducts() {
        System.out.println("Displaying all the products from the '" + databaseType + "' database...");
        products.forEach(product -> System.out.println("\t" + product));
    }
}
