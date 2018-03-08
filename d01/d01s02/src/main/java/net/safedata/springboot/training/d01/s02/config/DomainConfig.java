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

    /*
        This bean is needed just in case we need to use a bean of ProductRepository type.
        Otherwise, we can just use a private method.
     */
    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean
    public ProductService productService() {
        return new ProductService(productRepository());
    }

    /* --> the property setting method
    @Bean
    public ProductService productService() {
        final ProductService productService = new ProductService();
        productService.setProductRepository(productRepository());   --> might be omitted / forgotten
        return productService;
    }
    */
}
