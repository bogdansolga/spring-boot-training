package net.safedata.springboot.training.d02.s05.config;

import net.safedata.spring.training.jpa.model.Product;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * A simple {@link javax.sql.DataSource} configuration, which:
 * <ul>
 *     <li>configures the JPA repositories, using the {@link EnableJpaRepositories} annotation</li>
 *     <li>inserts a simple {@link Product} in the database, using the auto-configured {@link javax.sql.DataSource}</li>
 * </ul>
 */
@Configuration
@EnableJpaRepositories(basePackages = "net.safedata.springboot.training.d02.s05.repository")
@EntityScan(basePackages = "net.safedata.spring.training.jpa.model")
@EnableTransactionManagement
public class DataSourceConfig {
}
