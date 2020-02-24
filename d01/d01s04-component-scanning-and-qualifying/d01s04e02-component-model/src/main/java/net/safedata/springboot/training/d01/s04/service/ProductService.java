package net.safedata.springboot.training.d01.s04.service;

import net.safedata.springboot.training.d01.s04.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A simple product {@link Service}, which uses the {@link ProductRepository} as an {@link Autowired} collaborator
 *
 * @author bogdan.solga
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductProcessor productProcessor;

    @Autowired
    public ProductService(final ProductRepository productRepository, final ProductProcessor productProcessor) {
        this.productRepository = productRepository;
        this.productProcessor = productProcessor;
    }

    // read
    public void displayProducts() {
        productRepository.displayProducts();
    }

    // + many other CRUD operations
}
