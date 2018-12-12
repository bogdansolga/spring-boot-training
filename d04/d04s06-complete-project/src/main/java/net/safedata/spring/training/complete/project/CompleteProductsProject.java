package net.safedata.spring.training.complete.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Spring Boot project which showcases an end-to-end built project
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class CompleteProductsProject {

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(CompleteProductsProject.class);
        application.setAdditionalProfiles(Profiles.DEV);
        application.run(args);
    }
}
