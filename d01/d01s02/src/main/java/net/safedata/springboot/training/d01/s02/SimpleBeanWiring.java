package net.safedata.springboot.training.d01.s02;

import net.safedata.springboot.training.d01.s02.config.DomainConfig;
import net.safedata.springboot.training.d01.s02.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A simple demo for the usage of a Spring {@link org.springframework.context.annotation.Bean} which has a wired
 * collaborator
 *
 * @author bogdan.solga
 */
public class SimpleBeanWiring {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DomainConfig.class);

        // retrieving and using the ProductService; it will have the dependency wired (injected)
        final ProductService productService = applicationContext.getBean(ProductService.class);
        productService.displayProducts();
    }
}
