package net.safedata.spring.data.jdbc.config;

import net.safedata.spring.data.jdbc.domain.entity.Product;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.JdbcConfiguration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableJdbcRepositories(basePackages = "net.safedata.spring.data.jdbc.domain.repository")
@Import(JdbcConfiguration.class)
public class PersistenceConfig {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Bean
    public ApplicationListener<BeforeSaveEvent> timeStampingSaveTime() {
        return event -> {
            final Object entity = event.getEntity();
            if (entity instanceof Product) {
                final Product product = (Product) entity;
                product.setId(atomicInteger.incrementAndGet());
            }
        };
    }

}
