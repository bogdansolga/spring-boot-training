package net.safedata.springboot.training.d01.s03.config;

import net.safedata.springboot.training.d01.s03.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A simple Spring configuration, which exposes a single aliased bean
 *
 * @author bogdan.solga
 */
@Configuration
public class BeanAliasingConfig {

    // the specified name[s] will become the bean ID[s]
    @Bean(name = { // --> AKA
            "productRepository",
            "repository",
            "prodRepo"
    })
    public ProductRepository productRepository() {
        return new ProductRepository();
    }
}
