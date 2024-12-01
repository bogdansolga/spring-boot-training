package net.safedata.springboot.training.d02.s02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;

/**
 * A simple Spring {@link Configuration} which demos the usage of configuration files per activated
 * {@link org.springframework.context.annotation.Profile}
 */
@Configuration
public class ProfileConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileConfig.class);

    private final Environment environment;

    @Value("${connection.timeout}")
    private int connectionTimeout;

    @Autowired
    public ProfileConfig(final Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        LOGGER.info("\tThe value of the 'connection.timeout' for the '{}' profile is '{}'",
                Arrays.asList(environment.getActiveProfiles()), connectionTimeout);
    }
}
