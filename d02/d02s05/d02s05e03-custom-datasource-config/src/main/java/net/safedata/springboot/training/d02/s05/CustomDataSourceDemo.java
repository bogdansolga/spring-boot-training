package net.safedata.springboot.training.d02.s05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * A small Spring Boot app used to demo the usage of a local PostgreSQL database, using a custom configured
 * {@link javax.sql.DataSource}
 *
 * @author bogdan.solga
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CustomDataSourceDemo {

    public static void main(String[] args) {
        SpringApplication.run(CustomDataSourceDemo.class, args);
    }
}
