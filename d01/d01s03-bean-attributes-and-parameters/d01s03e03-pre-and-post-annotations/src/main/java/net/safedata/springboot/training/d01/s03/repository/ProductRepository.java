package net.safedata.springboot.training.d01.s03.repository;

import javax.annotation.PostConstruct;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
public class ProductRepository {

    @PostConstruct
    public void init() {
        System.out.println("Initializing the repository, using the PostConstruct annotation...");
    }

    public void displayProducts() {
        System.out.println("Displaying all the products");
    }
}
