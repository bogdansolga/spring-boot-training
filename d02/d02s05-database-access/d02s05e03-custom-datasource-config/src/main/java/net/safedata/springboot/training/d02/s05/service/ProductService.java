package net.safedata.springboot.training.d02.s05.service;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.springboot.training.d02.s05.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        create(new Product("A nice and useful tablet", 250d));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void create(final Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Product get(final int id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Not found"));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(final int id, final Product product) {
        final Product existingProduct = get(id);

        existingProduct.setName(product.getName());

        productRepository.save(product);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(final int id) {
        productRepository.deleteById(id);
    }
}
