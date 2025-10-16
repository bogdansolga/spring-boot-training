package net.safedata.springboot.training.d03.s04.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import net.safedata.springboot.training.d03.s04.model.Product;
import net.safedata.springboot.training.d03.s04.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final Gauge gauge;
    private final Counter counter;

    @Autowired
    public ProductService(final ProductRepository productRepository, final MeterRegistry meterRegistry) {
        this.productRepository = productRepository;
        Counter.builder("products.updatedProducts")
               .register(meterRegistry);

        gauge = Gauge.builder("products.gauge", () -> new AtomicInteger(0))
                     .register(meterRegistry);
        counter = Counter.builder("products.counter")
                         .register(meterRegistry);
    }

    public void create(final Product product) {
        productRepository.save(product);
    }

    public Product get(final int id) {
        counter.increment();
        return productRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Not found"));
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public void update(final int id, final Product product) {
        final Product existingProduct = get(id);

        counter.increment();
        gauge.value();

        existingProduct.setName(product.getName());

        productRepository.save(product);
    }

    public void delete(final int id) {
        productRepository.deleteById(id);
    }
}
