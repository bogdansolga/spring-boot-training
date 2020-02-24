package net.safedata.springboot.training.d01.s05;

import net.safedata.springboot.training.d01.s05.config.BeanProfilesConfig;
import net.safedata.springboot.training.d01.s05.config.ProfileEnabledConfig;
import net.safedata.springboot.training.d01.s05.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A simple demo of a Spring project to demo the usage of {@link org.springframework.context.annotation.Bean}s
 * activated by the command-line per {@link org.springframework.context.annotation.Profile}s
 *
 * @author bogdan.solga
 */
@SuppressWarnings("unused")
public class BeanProgrammaticProfilesDemo {

    private static final String PROFILE_ACTIVATION_PROPERTY = "spring.profiles.active";

    private static final String DEFAULT_PROFILE_SETTING_PROPERTY = "spring.profiles.default";

    // usually passed as a '-Dspring.profiles.active=<profiles>' run argument
    public static void main(String[] args) {
        //System.setProperty(PROFILE_ACTIVATION_PROPERTY, RunProfiles.PROD);
        System.setProperty("spring.profiles.active", "dev");

        final ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BeanProfilesConfig.class, ProfileEnabledConfig.class);

        final ProductService productService = applicationContext.getBean(ProductService.class);
        productService.displayProducts();
    }
}
