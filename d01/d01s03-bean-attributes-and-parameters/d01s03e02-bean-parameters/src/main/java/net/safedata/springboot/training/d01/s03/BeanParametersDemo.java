package net.safedata.springboot.training.d01.s03;

import net.safedata.springboot.training.d01.s03.config.BeanParametersConfig;
import net.safedata.springboot.training.d01.s03.service.ProductService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A simple demo for the usage of a Spring {@link org.springframework.context.annotation.Bean} with configured
 * 'init' and 'destroy' methods
 *
 * @author bogdan.solga
 */
public class BeanParametersDemo {

    private static final boolean USE_ANNOTATIONS_CONFIG = true;

    public static void main(String[] args) {
        // we are using a ConfigurableApplicationContext, in order to be able to close it
        final ConfigurableApplicationContext applicationContext = buildApplicationContext();

        final ProductService productService = applicationContext.getBean(ProductService.class);
        productService.displayProducts();

        // we need to close the application context, in order for the IoC container to invoke the configured 'destroy' methods
        applicationContext.close();
    }

    private static ConfigurableApplicationContext buildApplicationContext() {
        return USE_ANNOTATIONS_CONFIG ? new AnnotationConfigApplicationContext(BeanParametersConfig.class) :
                new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
