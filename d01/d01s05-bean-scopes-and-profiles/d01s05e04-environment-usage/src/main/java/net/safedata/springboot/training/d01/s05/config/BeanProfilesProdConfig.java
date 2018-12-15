package net.safedata.springboot.training.d01.s05.config;

import net.safedata.springboot.training.d01.s05.RunProfiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * A Spring configuration which wires several {@link Bean}s according to the used {@link Profile}
 *
 * @author bogdan.solga
 */
@Configuration
@ComponentScan(basePackages = "net.safedata.springboot.training.d01.s05")
@PropertySource("application-prod.properties")
@Profile(RunProfiles.PROD)
public class BeanProfilesProdConfig {
}
