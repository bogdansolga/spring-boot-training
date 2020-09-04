package net.safedata.springboot.training.d01.s04.config;

import net.safedata.springboot.training.d01.s04.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static net.safedata.springboot.training.d01.s04.repository.ProductRepository.*;

/**
 * A simple Spring configuration, which exposes two {@link ProductRepository} {@link Bean}s
 *
 * @author bogdan.solga
 */
@Configuration
@ComponentScan(basePackages = "net.safedata.springboot.training.d01.s04") // --> implicit config
public class BeanQualifyingConfig {

    @Bean(name = MY_SQL_REPO_BEAN_NAME) // --> explicit config
    public ProductRepository productRepository() {
        return new ProductRepository("MySQL");
    }

    @Bean(name = ORACLE_REPO_BEAN_NAME)
    public ProductRepository oracleProductRepository() {
        return new ProductRepository("Oracle");
    }
}
