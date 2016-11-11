package net.safedata.springboot.training.d01.s05.service;

import net.safedata.springboot.training.d01.s05.repository.ProductRepository;

/**
 * A simple {@link ProductService}, which uses the {@link ProductRepository} as a manually wired collaborator
 *
 * @author bogdan.solga
 */
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void displayProducts() {
        productRepository.displayProducts();
    }
}
