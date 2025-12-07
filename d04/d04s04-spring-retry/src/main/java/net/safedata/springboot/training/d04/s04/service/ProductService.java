package net.safedata.springboot.training.d04.s04.service;

import net.safedata.springboot.training.d04.s04.dto.ProductDTO;
import net.safedata.springboot.training.d04.s04.exceptions.NotFoundException;
import net.safedata.springboot.training.d04.s04.model.Product;
import net.safedata.springboot.training.d04.s04.repository.ProductRepository;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.retry.RetryException;
import org.springframework.core.retry.RetryListener;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@SuppressWarnings("unused")
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Retryable(
            includes = NotFoundException.class,
            maxRetries = 4,
            delay = 100,
            jitter = 10,
            multiplier = 1.2,
            maxDelay = 1000
    )
    public ProductDTO get(final int id) {
        LOGGER.info("Getting the product with the ID {}...", id);

        final Product product =
                Optional.ofNullable(productRepository.get(id))
                        .orElseThrow(() -> new NotFoundException("There is no product with the id " + id));

        return getProductConverter().apply(product);
    }

    private void usingRetryTemplate() {
        final RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(RetryPolicy.withMaxRetries(5));
        retryTemplate.setRetryListener(new SimpleRetryListener());
        try {
            retryTemplate.execute(() -> productRepository.get(1));
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage(), throwable);
        }
    }

    private Function<Product, ProductDTO> getProductConverter() {
        return product -> new ProductDTO(product.getId(), product.getName());
    }

    private static class SimpleRetryListener implements RetryListener {
        @Override
        public void beforeRetry(RetryPolicy retryPolicy, org.springframework.core.retry.Retryable<?> retryable) {
            RetryListener.super.beforeRetry(retryPolicy, retryable);
        }

        @Override
        public void onRetrySuccess(RetryPolicy retryPolicy, org.springframework.core.retry.Retryable<?> retryable, @Nullable Object result) {
            RetryListener.super.onRetrySuccess(retryPolicy, retryable, result);
        }

        @Override
        public void onRetryFailure(RetryPolicy retryPolicy, org.springframework.core.retry.Retryable<?> retryable, Throwable throwable) {
            RetryListener.super.onRetryFailure(retryPolicy, retryable, throwable);
        }

        @Override
        public void onRetryPolicyExhaustion(RetryPolicy retryPolicy, org.springframework.core.retry.Retryable<?> retryable, RetryException exception) {
            RetryListener.super.onRetryPolicyExhaustion(retryPolicy, retryable, exception);
        }

        @Override
        public void onRetryPolicyInterruption(RetryPolicy retryPolicy, org.springframework.core.retry.Retryable<?> retryable, RetryException exception) {
            RetryListener.super.onRetryPolicyInterruption(retryPolicy, retryable, exception);
        }
    }
}
