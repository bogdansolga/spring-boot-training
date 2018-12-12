package net.safedata.springboot.training.d02.s04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A small Spring Boot demo used to showcase the Spring centralized exception handling,
 * using an {@link org.springframework.web.bind.annotation.ControllerAdvice} annotated class and several
 * {@link org.springframework.web.bind.annotation.ExceptionHandler} annotated methods
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class ExceptionHandlingDemo {

    public static void main(String[] args) {
        SpringApplication.run(ExceptionHandlingDemo.class, args);
    }
}
