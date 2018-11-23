package net.safedata.springboot.training.d03.s06.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * A simple {@link javax.sql.DataSource} configuration, which:
 * <ul>
 *     <li>configures the JPA repositories, using the {@link EnableJpaRepositories} annotation</li>
 *     <li>configures a custom {@link javax.sql.DataSource}, using the {@link HikariDataSource} class</li>
 * </ul>
 *
 * @author bogdan.solga
 */
@Configuration
@EnableJpaRepositories(basePackages = "net.safedata.springboot.training.d03.s06.repository")
@EnableTransactionManagement
public class DataSourceConfig {

    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    // if the @Bean is commented, the configured data-source will be wired
    @Primary
    @Bean
    public javax.sql.DataSource hikariConnectionPool() {
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setPoolName("hikari-connection-pool");
        hikariConfig.setMaximumPoolSize(AVAILABLE_PROCESSORS / 2);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setIdleTimeout(60000);
        hikariConfig.setMaxLifetime(120000);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);

        return new HikariDataSource(hikariConfig);
    }
}
