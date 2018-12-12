package net.safedata.springboot.training.d03.s04.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import net.safedata.springboot.training.d03.s04.model.Product;
import net.safedata.springboot.training.d03.s04.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository, final MeterRegistry meterRegistry) {
        this.productRepository = productRepository;
        Counter.builder("products.updatedProducts")
               .register(meterRegistry);
    }

    public void create(final Product product) {
        productRepository.save(product);
    }

    public Product get(final int id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Not found"));
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public void update(final int id, final Product product) {
        final Product existingProduct = get(id);

        final Counter counter = Metrics.counter("products.updatedProducts", "today", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        counter.increment();

        existingProduct.setName(product.getName());

        productRepository.save(product);
    }

    public void delete(final int id) {
        productRepository.deleteById(id);
    }
}
