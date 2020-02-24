package net.safedata.springboot.training.d02.s03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

/**
 * A small Spring Boot demo used to showcase serving static content from the embedded Tomcat
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class StaticContentServingDemo {

    public static void main(String[] args) {
        //SpringApplication.run(StaticContentServingDemo.class, args);
        final String string = UUID.randomUUID().toString();
        System.out.println(string + ", " + string.length());
    }
}
