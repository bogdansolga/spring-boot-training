package net.safedata.springboot.training.d01.s01.single.config;

import net.safedata.springboot.training.d01.s01.single.beans.HelloSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sample Spring configuration, which exposes a simple {@link Bean}
 *
 * @author bogdan.solga
 */
@Configuration
public class SingleBeanConfig {

    // the method 'helloSpring' creates a HelloSpring bean, as it has the @Bean annotation
    @Bean
    public HelloSpring helloSpring() {
        return new HelloSpring();
    }
}
