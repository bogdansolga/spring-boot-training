package net.safedata.springboot.training.d04.s04.service;

import net.safedata.springboot.training.d04.s04.dto.ProductDTO;
import net.safedata.springboot.training.d04.s04.exceptions.NotFoundException;
import net.safedata.springboot.training.d04.s04.model.Product;
import net.safedata.springboot.training.d04.s04.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
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

    @Retryable(
            include = NotFoundException.class,
            backoff = @Backoff(delay = 2000),
            maxAttempts = 5
    )
    public ProductDTO get(final int id) {
        LOGGER.info("Getting the product with the ID {}...", id);

        final Product product =
                Optional.ofNullable(productRepository.get(id))
                        .orElseThrow(() -> new NotFoundException("There is no product with the id " + id));

        final RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(5));
        try {
            retryTemplate.execute((RetryCallback<String, Throwable>) context -> "something");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return getProductConverter().apply(product);
    }

    private Function<Product, ProductDTO> getProductConverter() {
        return product -> new ProductDTO(product.getId(), product.getName());
    }
}
