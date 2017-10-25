package net.safedata.springboot.training.d03s03.service;

import net.safedata.springboot.training.d03s03.model.Product;
import net.safedata.springboot.training.d03s03.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private static final int THROWING_ID = 13;

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        saveProduct(new Product(1, "Tablet"));
        saveProduct(new Product(2, "Phone"));
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public Product getProduct(final int id) {
        if (id == THROWING_ID) {
            throw new IllegalArgumentException("There is no product with the ID " + THROWING_ID);
        }

        return productRepository.findOne(id);
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public List<Product> getProducts() {
        final List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED
    )
    public String saveProduct(final Product product) {
        // a lot of processing goes in here, before actually saving the product :)
        productRepository.save(product);
        return "OK";
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public List<Product> getAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                            .collect(Collectors.toList());
    }
}
