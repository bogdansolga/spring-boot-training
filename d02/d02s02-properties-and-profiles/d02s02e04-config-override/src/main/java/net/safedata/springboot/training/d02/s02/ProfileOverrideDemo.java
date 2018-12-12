package net.safedata.springboot.training.d02.s02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A demo on how the configured properties are overriden by the used {@link org.springframework.context.annotation.Profile}s
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class ProfileOverrideDemo {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(ProfileOverrideDemo.class);

        springApplication.setAdditionalProfiles(Profiles.DEV);

        springApplication.run(args);
    }
}
