package net.safedata.springboot.training.d04.s02;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Spring Boot class which demos the usage of Quartz scheduled tasks
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class ClusteredTaskSchedulingDemo {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(ClusteredTaskSchedulingDemo.class);
        springApplication.setLogStartupInfo(false);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setWebApplicationType(org.springframework.boot.WebApplicationType.NONE);

        springApplication.run(args);
    }
}
