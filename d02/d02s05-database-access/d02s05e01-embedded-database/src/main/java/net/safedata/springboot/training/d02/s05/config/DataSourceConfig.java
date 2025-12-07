package net.safedata.springboot.training.d02.s05.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * A simple {@link javax.sql.DataSource} configuration, which configures the JPA repositories,
 * using the {@link EnableJpaRepositories} annotation
 *
 * @author bogdan.solga
 */
@Configuration
@EnableJpaRepositories(basePackages = "net.safedata.springboot.training.d02.s05.repository")
@EntityScan(basePackages = "net.safedata.spring.training.jpa.model")
@EnableTransactionManagement
public class DataSourceConfig {
}
