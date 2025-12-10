package net.safedata.springboot.training.d02.s01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A minimal Spring Boot intro application
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class HelloSpringBoot {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HelloSpringBoot.class);
        application.setAdditionalProfiles("dev");
        application.run(args);
    }
}
