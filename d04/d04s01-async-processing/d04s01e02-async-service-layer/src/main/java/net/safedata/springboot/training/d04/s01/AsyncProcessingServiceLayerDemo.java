package net.safedata.springboot.training.d04.s01;

import net.safedata.springboot.training.d04.s01.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * A Spring Boot class which demos the async processing on the service layer
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class AsyncProcessingServiceLayerDemo implements CommandLineRunner {

    private final ProductService productService;

    @Autowired
    public AsyncProcessingServiceLayerDemo(final ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        productService.callAllAsyncMethods();
    }

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(AsyncProcessingServiceLayerDemo.class);
        springApplication.setLogStartupInfo(false);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setWebApplicationType(org.springframework.boot.WebApplicationType.NONE);

        final ConfigurableApplicationContext applicationContext = springApplication.run(args);
        applicationContext.close();
    }
}
