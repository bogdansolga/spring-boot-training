package net.safedata.springboot.training.d01.s04;

import net.safedata.springboot.training.d01.s04.config.ComponentModelConfig;
import net.safedata.springboot.training.d01.s04.controller.ProductController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A simple demo of a Spring project which uses auto-wiring for the declared {@link org.springframework.stereotype.Component}s
 *
 * @author bogdan.solga
 */
public class ComponentModelDemo {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentModelConfig.class);

        final ProductController productController = applicationContext.getBean(ProductController.class);
        productController.displayProducts();
    }
}
