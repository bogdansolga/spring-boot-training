package net.safedata.springboot.training.d04.s04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A small Spring Boot demo for demoing the Spring retry mechanism
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class SpringRetryDemo {

    public static void main(String[] args) {
        SpringApplication.run(SpringRetryDemo.class, args);
    }
}
