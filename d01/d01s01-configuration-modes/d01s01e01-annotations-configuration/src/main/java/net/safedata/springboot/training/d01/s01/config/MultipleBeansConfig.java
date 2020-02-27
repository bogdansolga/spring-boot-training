package net.safedata.springboot.training.d01.s01.config;

import net.safedata.spring.training.domain.model.Product;
import net.safedata.springboot.training.d01.s01.beans.HelloSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sample Spring configuration, which exposes several simple {@link Bean}s
 *
 * @author bogdan.solga
 */
@Configuration
@SuppressWarnings("unused")
public class MultipleBeansConfig {

    // the method 'helloSpring' creates a HelloSpring bean, as it has the @Bean annotation
    @Bean
    public HelloSpring helloSpring() {
        return new HelloSpring();
    }

    @Bean
    public HelloSpring otherHelloSpring() {
        return new HelloSpring();
    }

    @Bean
    public Product product() {
        return new Product(1, "Tablet", 200d);
    }

    @Bean
    public String helloSpringAsString() {
        return "Hello, Spring [as a string]!";
    }

    @Bean
    public Boolean booleanBean() {
        return Boolean.TRUE;
    }
}
