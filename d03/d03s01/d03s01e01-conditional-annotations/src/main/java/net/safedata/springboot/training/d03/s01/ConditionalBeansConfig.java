package net.safedata.springboot.training.d03.s01;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Spring {@link Configuration} which loads some beans conditionally
 *
 * @author bogdan.solga
 */
@Configuration
public class ConditionalBeansConfig {

    @Bean
    @ConditionalOnClass(DataSource.class)
    public String conditionalOnClass() {
        return "Conditional on the DataSource class";
    }

    @Bean
    @ConditionalOnProperty(name = "matching.property", havingValue = "true")
    public String conditionalOnProperty() {
        return "Conditional on a property";
    }

    @Bean
    @ConditionalOnProperty(name = "used.webserver", havingValue = "jetty")
    public EmbeddedServletContainer jetty() {
        return new JettyEmbeddedServletContainer(null, true);
    }

    // a 'feature toggle' sample approach
    @Bean
    @ConditionalOnProperty(name = "cool.feature", havingValue = "enabled")
    public Object coolFeature() {
        return new Object();
    }
}
