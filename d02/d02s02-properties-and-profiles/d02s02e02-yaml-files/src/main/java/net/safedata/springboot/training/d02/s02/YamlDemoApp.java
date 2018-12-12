package net.safedata.springboot.training.d02.s02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A simple Spring Boot app which demos the usage of a configuration file in YAML format (application.yml)
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class YamlDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(YamlDemoApp.class, args);
    }
}
