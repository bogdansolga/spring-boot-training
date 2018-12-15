package net.safedata.springboot.training.d01.s03.service;

import net.safedata.springboot.training.d01.s03.repository.ProductRepository;

import java.util.Optional;

/**
 * A simple product service, which uses a {@link ProductRepository} as a collaborator
 *
 * @author bogdan.solga
 */
public class ProductService {

    private final ProductRepository productRepository;
    private final String identifier;

    public ProductService(final ProductRepository productRepository, final String identifier) {
        this.productRepository = productRepository; this.identifier = identifier;
    }

    public void displayProducts() {
        System.out.println("Using the '" + identifier + "' service");
        Optional.ofNullable(productRepository)
                .ifPresent(ProductRepository::displayProducts);
    }
}
