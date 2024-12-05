package net.safedata.springboot.training.d01.s03;

import net.safedata.springboot.training.d01.s03.config.PrePostAnnotationsConfig;
import net.safedata.springboot.training.d01.s03.service.ProductService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A simple demo for the usage of a Spring {@link org.springframework.context.annotation.Bean}s with configured
 * {@link jakarta.annotation.PostConstruct} and {@link jakarta.annotation.PreDestroy} annotations
 *
 * @author bogdan.solga
 */
public class PrePostAnnotationsDemo {

    public static void main(String[] args) {
        // we are using a ConfigurableApplicationContext, in order to be able to close it
        final ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(PrePostAnnotationsConfig.class);

        final ProductService productService = applicationContext.getBean(ProductService.class);
        productService.displayProducts();

        // we need to close the application context, in order for the IoC container to invoke the '@PreDestroy' annotated methods
        applicationContext.close();
    }
}
