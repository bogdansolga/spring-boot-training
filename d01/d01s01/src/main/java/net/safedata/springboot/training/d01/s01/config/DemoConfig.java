package net.safedata.springboot.training.d01.s01.config;

import net.safedata.springboot.training.d01.s01.beans.HelloSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sample Spring configuration, which exposes two simple {@link Bean}s
 *
 * @author bogdan.solga
 */
@Configuration
public class DemoConfig {

    private final AnotherConfig anotherConfig;

    @Autowired
    public DemoConfig(AnotherConfig anotherConfig) {
        this.anotherConfig = anotherConfig;
    }

    @Bean
    public String helloSpringAsString() {
        return "Hello, Spring [as a string]!";
    }

    @Bean
    public HelloSpring helloSpring() {
        return new HelloSpring();
    }

    /**
     * This bean is commented by default, so that we won't have two beans of the same type in the Spring IoC container
     */
    //@Bean
    public HelloSpring otherHelloSpring() {
        return new HelloSpring();
    }
}
