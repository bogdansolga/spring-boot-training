package net.safedata.springboot.training.d03.s05;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * A small Spring Boot demo for packaging the app as a WAR (Web Archive) file.
 *
 * The {@link SpringBootServletInitializer} interface is used to initialize the Servlet context required by Tomcat
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class WarPackagingDemo extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(WarPackagingDemo.class)
                      .bannerMode(Banner.Mode.OFF)
                      .logStartupInfo(false);
    }

    public static void main(String[] args) {
        SpringApplication.run(WarPackagingDemo.class, args);
    }
}
