package net.safedata.springboot.training.d03.s04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A small Spring Boot demo used to demo the usage of the Spring Boot Actuator endpoints
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class ActuatorEndpointsDemo {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorEndpointsDemo.class, args);
    }
}
