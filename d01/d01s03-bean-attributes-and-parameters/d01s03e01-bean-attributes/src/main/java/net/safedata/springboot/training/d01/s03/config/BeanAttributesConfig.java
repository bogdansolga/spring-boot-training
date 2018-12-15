package net.safedata.springboot.training.d01.s03.config;

import net.safedata.springboot.training.d01.s03.repository.ProductRepository;
import net.safedata.springboot.training.d01.s03.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

/**
 * A simple Spring configuration, which wires two simple {@link Bean}s and exposes them as {@link Lazy} and {@link Primary} beans
 *
 * @author bogdan.solga
 */
@Configuration
public class BeanAttributesConfig {

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Primary
    @Bean
    public ProductService productService() {
        return new ProductService(productRepository(), "productService");
    }

    @Lazy
    @Bean
    public ProductService lazyProductService() throws InterruptedException {
        System.out.println("Initializing the lazy ProductService...");
        Thread.sleep(3000);
        return new ProductService(productRepository(), "lazyProductService");
    }
}
