package net.safedata.springboot.training.d02.s03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;

/**
 * A small Spring Boot demo used to showcase serving static content from the embedded Tomcat
 *
 * @author bogdan.solga
 */
@SpringBootApplication(exclude = {
        MultipartAutoConfiguration.class,
        JmxAutoConfiguration.class
})
public class StaticContentServingDemo {

    public static void main(String[] args) {
        SpringApplication.run(StaticContentServingDemo.class, args);
    }
}
