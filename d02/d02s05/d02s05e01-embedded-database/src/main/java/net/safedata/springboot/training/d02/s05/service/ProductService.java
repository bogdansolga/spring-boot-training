package net.safedata.springboot.training.d02.s05.service;

import net.safedata.springboot.training.d02.s05.model.Product;
import net.safedata.springboot.training.d02.s05.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final JdbcTemplate jdbcTemplate;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final JdbcTemplate jdbcTemplate, final ProductRepository productRepository) {
        this.jdbcTemplate = jdbcTemplate; this.productRepository = productRepository;
    }

    @Scheduled(fixedDelay = 10000)
    public void searchForProducts() {
        //TODO insert complex logic here

        jdbcTemplate.execute("SELECT * FROM product p WHERE p.name = ?");
    }

    public void create(final Product product) {
        productRepository.save(product);
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = {IllegalArgumentException.class, IllegalAccessException.class}
    )
    @Cacheable(cacheManager = "cacheManager", cacheNames = "products", condition = "id > 10")
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

    @CacheEvict(cacheNames = "products", condition = "id > 10")
    public void delete(final int id) {
        productRepository.delete(id);
    }
}
