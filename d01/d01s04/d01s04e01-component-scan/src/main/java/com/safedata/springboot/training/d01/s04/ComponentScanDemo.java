package com.safedata.springboot.training.d01.s04;

import net.safedata.springboot.training.d01.s04.config.ComponentScanConfig;
import net.safedata.springboot.training.d01.s04.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A simple demo of a Spring project which wires the {@link org.springframework.context.annotation.Bean}s through
 * {@link org.springframework.context.annotation.ComponentScan}ing
 *
 * @author bogdan.solga
 */
public class ComponentScanDemo {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentScanConfig.class);

        final ProductService productService = applicationContext.getBean(ProductService.class);
        productService.displayProducts();
    }
}
