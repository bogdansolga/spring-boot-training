package net.safedata.springboot.training.d01.s04.service;

import net.safedata.springboot.training.d01.s04.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * A simple product service, which uses a {@link ProductRepository} as a collaborator
 *
 * @author bogdan.solga
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(@Qualifier(value = "mySQLProductRepository") final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void displayProducts() {
        productRepository.displayProducts();
    }
}
