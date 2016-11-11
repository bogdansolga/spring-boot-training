package net.safedata.springboot.training.d01.s03.repository;

import org.springframework.stereotype.Repository;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
@Repository
public class ProductRepository {

    public void displayProducts() {
        System.out.println("Displaying all the products");
    }
}
