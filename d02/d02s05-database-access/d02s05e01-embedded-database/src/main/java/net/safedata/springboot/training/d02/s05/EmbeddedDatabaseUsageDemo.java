package net.safedata.springboot.training.d02.s05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A small Spring Boot demo used to demo the usage of an embedded H2 database
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class EmbeddedDatabaseUsageDemo {

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedDatabaseUsageDemo.class, args);
    }
}
