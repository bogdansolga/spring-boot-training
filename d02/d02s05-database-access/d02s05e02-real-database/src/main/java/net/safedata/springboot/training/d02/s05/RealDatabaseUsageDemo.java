package net.safedata.springboot.training.d02.s05;

import jakarta.annotation.PostConstruct;
import net.safedata.spring.training.jpa.model.Product;
import net.safedata.springboot.training.d02.s05.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * A small Spring Boot app used to demo the usage of a local PostgreSQL database
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class RealDatabaseUsageDemo {

    private static final Random RANDOM = new Random(200);

    public static void main(String[] args) {
        SpringApplication.run(RealDatabaseUsageDemo.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(ProductService productService) {
        return args -> {
            IntStream.range(0, 10)
                     .forEach(value ->
                             productService.create(new Product("The product with the ID " + RANDOM.nextInt(100),
                                     RANDOM.nextDouble() * 200))
                     );
        };
    }
}
