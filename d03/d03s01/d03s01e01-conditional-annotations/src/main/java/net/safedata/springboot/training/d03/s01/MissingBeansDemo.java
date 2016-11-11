package net.safedata.springboot.training.d03.s01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Spring Boot demo which demos the usage of {@link org.springframework.context.annotation.Conditional} annotations
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class MissingBeansDemo {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = SpringApplication.run(MissingBeansDemo.class, args);

        final String conditionalOnClass = applicationContext.getBean("conditionalOnClass", String.class);
        System.out.println("conditionalOnClass bean was found - " + conditionalOnClass);

        //final String conditionalOnProperty = applicationContext.getBean("conditionalOnProperty", String.class);
        //System.out.println("conditionalOnProperty bean was found - " + conditionalOnProperty);
    }
}
