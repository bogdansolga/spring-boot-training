package net.safedata.springboot.training.d01.s02.config;

import net.safedata.springboot.training.d01.s02.repository.ProductRepository;
import net.safedata.springboot.training.d01.s02.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A simple Spring configuration, which wires two simple {@link Bean}s
 *
 * @author bogdan.solga
 */
@Configuration
public class DomainConfig {

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean
    public ProductService productService() {
        final ProductService productService = new ProductService();
        productService.setProductRepository(productRepository());
        return productService;
    }
}
