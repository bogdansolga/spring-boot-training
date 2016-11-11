package net.safedata.springboot.training.d03.s04.service;

import net.safedata.springboot.training.d03.s04.model.Product;
import net.safedata.springboot.training.d03.s04.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(final Product product) {
        productRepository.save(product);
    }

    public Product get(final int id) {
        return productRepository.findOne(id);
    }

    @Cacheable
    @EventListener(ContextRefreshedEvent.class)
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public void update(final int id, final Product product) {
        final Product existingProduct = productRepository.findOne(id);

        existingProduct.setName(product.getName());

        productRepository.save(product);
    }

    public void delete(final int id) {
        productRepository.delete(id);
    }
}
