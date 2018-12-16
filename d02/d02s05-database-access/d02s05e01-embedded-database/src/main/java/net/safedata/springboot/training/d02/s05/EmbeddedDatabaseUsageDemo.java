package net.safedata.springboot.training.d02.s05;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.springboot.training.d02.s05.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

/**
 * A small Spring Boot demo used to demo the usage of an embedded H2 database
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class EmbeddedDatabaseUsageDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedDatabaseUsageDemo.class);

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedDatabaseUsageDemo.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(final ProductService productService) {
        return args -> {
            IntStream.range(0, 50)
                     .forEach(id ->  productService.create(new Product("The product #" + id)));
            LOGGER.info("The default products were successfully created!");
        };
    }
}
