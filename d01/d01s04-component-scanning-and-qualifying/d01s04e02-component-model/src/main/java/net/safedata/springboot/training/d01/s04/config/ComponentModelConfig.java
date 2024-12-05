package net.safedata.springboot.training.d01.s04.config;

import net.safedata.springboot.training.d01.s04.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * A simple Spring configuration, which wires the beans automatically via {@link ComponentScan}ing
 *
 * @author bogdan.solga
 */
@Configuration
@ComponentScan(basePackages = "net.safedata.springboot.training.d01.s04") // implicit wiring --> 98-99% from a project
public class ComponentModelConfig {

    @Bean
    public ProductRepository customProductRepository() { // explicit wiring --> ~1% from a project
        return new ProductRepository();
    }
}
