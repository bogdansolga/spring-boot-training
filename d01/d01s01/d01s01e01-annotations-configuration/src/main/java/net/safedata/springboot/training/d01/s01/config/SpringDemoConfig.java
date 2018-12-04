package net.safedata.springboot.training.d01.s01.config;

import net.safedata.springboot.training.d01.s01.beans.HelloSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sample Spring configuration, which exposes two simple {@link Bean}s
 *
 * @author bogdan.solga
 */
@Configuration
@SuppressWarnings("unused")
public class SpringDemoConfig {

    // the method 'helloSpring' creates a HelloSpring bean, as it has the @Bean annotation
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

    @Bean
    public String helloSpringAsString() {
        return "Hello, Spring [as a string]!";
    }

    @Bean
    public Boolean booleanBean() {
        return Boolean.TRUE;
    }
}
