package net.safedata.springboot.training.d01.s05.config;

import net.safedata.springboot.training.d01.s05.RunProfiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * An example of a Spring {@link Configuration} enabled only when a {@link Profile} is used
 *
 * @author bogdan.solga
 */
@Configuration
@Profile({
        RunProfiles.PROD,
        RunProfiles.TOMCAT
})
public class ProfileEnabledConfig {

    @Bean
    public String prodOnlyBean() {
        System.out.println("Enabling the 'prod-only' bean...");
        return "prod-only";
    }
}
