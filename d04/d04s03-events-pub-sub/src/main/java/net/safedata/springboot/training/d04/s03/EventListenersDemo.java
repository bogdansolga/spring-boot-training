package net.safedata.springboot.training.d04.s03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Spring Boot class which demos the usage of several {@link org.springframework.context.event.EventListener}
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class EventListenersDemo {

    public static void main(String[] args) {
        new SpringApplication(EventListenersDemo.class).run(args);
    }
}
