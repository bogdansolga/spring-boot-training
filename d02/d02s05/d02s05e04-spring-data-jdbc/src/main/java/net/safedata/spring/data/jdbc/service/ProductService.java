package net.safedata.spring.data.jdbc.service;

import net.safedata.spring.data.jdbc.domain.entity.Product;
import net.safedata.spring.data.jdbc.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    @Transactional(readOnly = false)
    public void init() {
        productRepository.save(new Product(1, "Tablet", 204d));
        System.out.println("Saved");
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
}
