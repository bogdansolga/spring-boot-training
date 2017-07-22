package net.safedata.springboot.training.d02.s05.service;

import net.safedata.springboot.training.d02.s05.model.Product;
import net.safedata.springboot.training.d02.s05.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    public void bootstrap() {
        final Product product = new Product();
        product.setName("iSomething");
        product.setPrice(250d);

        create(product);
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public void create(final Product product) {
        productRepository.save(product);
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public Product get(final int id) {
        return productRepository.findOne(id);
    }

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
