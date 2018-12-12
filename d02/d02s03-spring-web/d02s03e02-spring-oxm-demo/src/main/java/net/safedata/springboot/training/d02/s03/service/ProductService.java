package net.safedata.springboot.training.d02.s03.service;

import net.safedata.springboot.training.d02.s03.model.Product;
import net.safedata.springboot.training.d02.s03.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(final Product product) {
        productRepository.create(product);
    }

    public Product get(final int id) {
        return productRepository.get(id);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public void update(final int id, final Product product) {
        productRepository.update(id, product);
    }

    public void delete(final int id) {
        productRepository.delete(id);
    }
}
