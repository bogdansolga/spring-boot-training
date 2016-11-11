package net.safedata.springboot.training.d01.s05;

import net.safedata.springboot.training.d01.s05.config.BeanProfilesConfig;
import net.safedata.springboot.training.d01.s05.config.Profiles;
import net.safedata.springboot.training.d01.s05.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * An example of how to use a Spring {@link org.springframework.core.env.Environment}
 */
public class EnvironmentUsageDemo {

    private static final String PROFILES_ACTIVATION_PROPERTY = "spring.profiles.active";

    public static void main(String[] args) {
        System.setProperty(PROFILES_ACTIVATION_PROPERTY, Profiles.PROD);

        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanProfilesConfig.class);

        final ProductService productService = applicationContext.getBean(ProductService.class);
        productService.displayProducts();
    }
}
