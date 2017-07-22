package net.safedata.springboot.training.d03.s01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Spring Boot class which demos the integration of Spring Security
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class SpringSecurityDemo {

    public static void main(String[] args) {
        new SpringApplication(SpringSecurityDemo.class).run(args);
    }
}
