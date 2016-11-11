package net.safedata.springboot.training.d02.s02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A simple Spring Boot app which demos the usage of a configuration file in {@link java.util.Properties} format
 * (application.properties)
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class PropertiesDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(PropertiesDemoApp.class, args);
    }
}
