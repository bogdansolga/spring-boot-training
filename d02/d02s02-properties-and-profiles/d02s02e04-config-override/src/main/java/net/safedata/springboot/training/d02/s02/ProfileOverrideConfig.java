package net.safedata.springboot.training.d02.s02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A simple Spring {@link Configuration} which demos the usage of configuration files per activated
 * {@link org.springframework.context.annotation.Profile}
 */
@Configuration
public class ProfileOverrideConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileOverrideConfig.class);

    private final Environment environment;

    @Value("${connection.timeout}")
    private int connectionTimeout;

    @Autowired
    public ProfileOverrideConfig(final Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        LOGGER.info("\tThe value of the 'connection.timeout' for the '{}' profile is '{}'",
                getUsedProfiles(), connectionTimeout);
    }

    private String getUsedProfiles() {
        final String[] activeProfiles = environment.getActiveProfiles();
        return activeProfiles.length >= 1 ? Arrays.stream(activeProfiles)
                     .collect(Collectors.joining(",")) : "default";
    }
}
