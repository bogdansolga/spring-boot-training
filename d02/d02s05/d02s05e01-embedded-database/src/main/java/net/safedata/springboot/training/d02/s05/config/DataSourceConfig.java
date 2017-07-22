package net.safedata.springboot.training.d02.s05.config;

import net.safedata.springboot.training.d02.s05.model.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * A simple {@link javax.sql.DataSource} configuration, which:
 * <ul>
 *     <li>configures the JPA repositories, using the {@link EnableJpaRepositories} annotation</li>
 *     <li>inserts a simple {@link Product} in the auto-wired [in-memory] database</li>
 * </ul>
 *
 * @author bogdan.solga
 */
@Configuration
@EnableJpaRepositories(basePackages = "net.safedata.springboot.training.d02.s05.repository")
@EnableTransactionManagement
public class DataSourceConfig {
}
