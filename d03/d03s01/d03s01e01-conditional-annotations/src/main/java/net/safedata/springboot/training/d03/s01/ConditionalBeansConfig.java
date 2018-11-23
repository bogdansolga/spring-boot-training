package net.safedata.springboot.training.d03.s01;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
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
    @ConditionalOnProperty(
            name = "enable.important.and.experimental.feature",
    		havingValue = "true"
	)
    public String conditionalOnProperty() {
        System.out.println("Enabling our important [and experimental] feature...");
        return "Conditional on a property";
    }

    @Bean
    @ConditionalOnProperty(
    		name = "used.webserver",
    		havingValue = "jetty"
	)
    public JettyServletWebServerFactory jetty() {
        return new JettyServletWebServerFactory(9999);
    }

    // a 'feature toggle' sample approach
    @Bean
    @ConditionalOnProperty(
            name = "cool.feature",
            havingValue = "enabled"
    )
    public Object coolFeature() {
        System.out.println("Enabling the cool feature...");
        return new Object();
    }
}
