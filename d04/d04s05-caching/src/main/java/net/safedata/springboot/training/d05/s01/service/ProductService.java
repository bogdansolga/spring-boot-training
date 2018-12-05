package net.safedata.springboot.training.d05.s01.service;

import net.safedata.springboot.training.d05.s01.dto.ProductDTO;
import net.safedata.springboot.training.d05.s01.exceptions.NotFoundException;
import net.safedata.springboot.training.d05.s01.model.Product;
import net.safedata.springboot.training.d05.s01.repository.ProductRepository;
import net.safedata.springboot.training.d05.s01.config.CacheNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(
            cacheNames = CacheNames.PRODUCTS,
            key = "#id"
    )
    public ProductDTO get(final int id) {
        LOGGER.info("Getting the product with the ID {}...", id);

        final Product product =
                Optional.ofNullable(productRepository.get(id))
                        .orElseThrow(() -> new NotFoundException("There is no product with the id " + id));

        return getProductConverter().apply(product);
    }

    @Async
    @EventListener
    @CacheEvict(
            cacheNames = CacheNames.PRODUCTS,
            key = "#id"
    )
    public void removeProduct(final int id) {
        productRepository.remove(id);
    }

    private Function<Product, ProductDTO> getProductConverter() {
        return product -> new ProductDTO(product.getId(), product.getName());
    }
}
