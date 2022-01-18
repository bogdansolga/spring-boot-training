package net.safedata.springboot.training.d02.s02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * A simple Spring {@link Configuration} which demos the usage of configuration files per activated
 *
 * @author bogdan.solga
 */
@Configuration
@PropertySource("classpath:product.properties")
@PropertySource("file:${user.dir}/d02/d02s02/d02s02e05-loading-properties-files/src/main/an-external-file.properties")
public class PropertiesLoadingConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoadingConfig.class);

    @Value("${product.name}")
    private String productName;

    @Value("${product.price}")
    private double productPrice;

    @Value("${external.property:default}")
    private String externalProperty;

    @PostConstruct
    public void init() {
        LOGGER.info("The product name is '{}', the product price is {}", productName, productPrice);
        LOGGER.info("The external property is '{}'", externalProperty);
    }
}
