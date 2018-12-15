package net.safedata.springboot.training.d01.s04;

import net.safedata.springboot.training.d01.s04.config.BeanQualifyingConfig;
import net.safedata.springboot.training.d01.s04.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A simple demo of a Spring project to demo the {@link org.springframework.context.annotation.Bean} aliasing
 *
 * @author bogdan.solga
 */
public class BeanQualifyingDemo {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanQualifyingConfig.class);

        final ProductService productService = applicationContext.getBean(ProductService.class);
        productService.displayProducts();
    }
}
