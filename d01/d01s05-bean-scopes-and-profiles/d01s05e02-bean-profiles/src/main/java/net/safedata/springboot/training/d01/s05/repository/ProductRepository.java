package net.safedata.springboot.training.d01.s05.repository;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
public class ProductRepository {

    private final String databaseType;

    public ProductRepository(final String databaseType) {
        this.databaseType = databaseType;
    }

    public void displayProducts() {
        System.out.println("Displaying all the products from the '" + databaseType + "' database...");
    }
}
