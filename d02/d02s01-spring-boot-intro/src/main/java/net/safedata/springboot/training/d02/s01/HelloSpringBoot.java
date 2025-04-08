package net.safedata.springboot.training.d02.s01;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * A minimal Spring Boot intro application
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class HelloSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBoot.class, args);
    }

    @Bean
    ApplicationRunner init() {
        return args -> System.out.println("This runs on startup");
    }
}
