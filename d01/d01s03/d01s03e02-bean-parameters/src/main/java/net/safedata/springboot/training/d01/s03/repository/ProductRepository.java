package net.safedata.springboot.training.d01.s03.repository;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
public class ProductRepository {

    public void init() {
        System.out.println("Initializing the repository...");
    }

    public void displayProducts() {
        System.out.println("Displaying all the products");
    }
}
