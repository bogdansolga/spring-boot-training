package net.safedata.springboot.training.d02.s02;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A simple Spring Boot app which demos the usage of several Spring {@link org.springframework.context.annotation.Profile}s
 * with their corresponding configuration files
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class ProfileConfigDemo {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(ProfileConfigDemo.class);

        // running without a profile will throw an exception, as the property doesn't exist in the default config
        springApplication.setAdditionalProfiles(Profiles.PROD);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setLogStartupInfo(false);

        springApplication.run(args);
    }
}
