package net.safedata.springboot.training.d02.s02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A demo on loading properties files, using the {@link org.springframework.context.annotation.PropertySource} annotation
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class PropertiesLoadingDemo {

    public static void main(String[] args) {
        new SpringApplication(PropertiesLoadingDemo.class).run(args);
    }
}
