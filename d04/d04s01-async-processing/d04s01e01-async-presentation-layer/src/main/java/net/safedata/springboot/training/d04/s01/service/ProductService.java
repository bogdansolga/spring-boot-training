package net.safedata.springboot.training.d04.s01.service;

import net.safedata.springboot.training.d04.s01.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public Product getById(int id) {
        longRunningOperation();
        return new Product(id, "Mărțișor");
    }

    private void longRunningOperation() {
        try {
            LOGGER.info("Running a long running operation...");
            Thread.sleep(3000);
            //throw new IllegalArgumentException("Oops :)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
