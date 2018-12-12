package net.safedata.spring.data.jdbc.service;

import net.safedata.spring.data.jdbc.domain.entity.Product;
import net.safedata.spring.data.jdbc.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
}
