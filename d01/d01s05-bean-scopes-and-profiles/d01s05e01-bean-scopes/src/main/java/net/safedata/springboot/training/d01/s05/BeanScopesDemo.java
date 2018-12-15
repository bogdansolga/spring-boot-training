package net.safedata.springboot.training.d01.s05;

import net.safedata.springboot.training.d01.s05.config.BeanScopesConfig;
import net.safedata.springboot.training.d01.s05.service.ProductPrototypeService;
import net.safedata.springboot.training.d01.s05.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.IntStream;

/**
 * A simple demo of a Spring project to demo the {@link org.springframework.context.annotation.Bean} scopes
 *
 * @author bogdan.solga
 */
public class BeanScopesDemo {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanScopesConfig.class);

        displayingTheBeanHashCode(applicationContext);

        usingThePrototypeBean(applicationContext);
    }

    private static void displayingTheBeanHashCode(ApplicationContext applicationContext) {
        final ProductPrototypeService prototypeService = applicationContext.getBean(ProductPrototypeService.class);
        prototypeService.displayHashCode();

        final ProductPrototypeService anotherInstance = applicationContext.getBean(ProductPrototypeService.class);
        anotherInstance.displayHashCode();
    }

    private static void usingThePrototypeBean(ApplicationContext applicationContext) {
        // using a prototype scoped bean
        final ProductService productService = applicationContext.getBean(ProductService.class);

        IntStream.range(1, 5)
                 .forEach(value -> productService.processUsingPrototypeService((value)));

    }
}
