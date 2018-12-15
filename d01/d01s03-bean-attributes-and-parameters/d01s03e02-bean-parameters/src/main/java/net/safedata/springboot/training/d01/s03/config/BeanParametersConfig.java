package net.safedata.springboot.training.d01.s03.config;

import net.safedata.springboot.training.d01.s03.repository.ProductRepository;
import net.safedata.springboot.training.d01.s03.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A simple Spring configuration, which wires two simple {@link Bean}s and configures their init and destroy methods
 *
 * @author bogdan.solga
 */
@Configuration
public class BeanParametersConfig {

    @Bean(initMethod = "initialize")
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean(destroyMethod = "onClose")
    public ProductService productService() {
        return new ProductService(productRepository());
    }
}
