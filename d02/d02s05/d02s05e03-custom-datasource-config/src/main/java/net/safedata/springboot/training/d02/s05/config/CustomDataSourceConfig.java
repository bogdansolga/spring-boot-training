package net.safedata.springboot.training.d02.s05.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * A simple {@link javax.sql.DataSource} configuration, which:
 * <ul>
 *     <li>configures the JPA repositories, using the {@link EnableJpaRepositories} annotation</li>
 *     <li>configures a custom {@link DataSource}, using the {@link HikariDataSource} class</li>
 * </ul>
 */
@Configuration
@EnableJpaRepositories(basePackages = "net.safedata.springboot.training.d02.s05.repository")
@EnableTransactionManagement
public class CustomDataSourceConfig {

    // as properties from YAML files cannot be wired using @Value, we need to use a RelaxedPropertyResolver
    // to read the config values
    private final RelaxedPropertyResolver propertyResolver;

    @Autowired
    public CustomDataSourceConfig(final Environment environment) {
        propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }

    // if the @Bean is commented, the configured data-source will be wired
    @Bean
    public DataSource dataSource() {
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setPoolName("spring-boot-demo-pool");
        hikariConfig.setMaximumPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        hikariConfig.setJdbcUrl(propertyResolver.getProperty("url"));
        hikariConfig.setUsername(propertyResolver.getProperty("username"));
        hikariConfig.setPassword(propertyResolver.getProperty("password"));

        return new HikariDataSource(hikariConfig);
    }
}
