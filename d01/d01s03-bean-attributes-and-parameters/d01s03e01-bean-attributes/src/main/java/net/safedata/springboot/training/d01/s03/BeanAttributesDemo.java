package net.safedata.springboot.training.d01.s03;

import net.safedata.springboot.training.d01.s03.config.BeanAttributesConfig;
import net.safedata.springboot.training.d01.s03.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A simple demo for the usage of a Spring {@link org.springframework.context.annotation.Bean} with configured
 * initialization attributes
 *
 * @author bogdan.solga
 */
public class BeanAttributesDemo {

    private static final boolean USE_ANNOTATIONS_CONFIG = true;

    public static void main(String[] args) {
        final ApplicationContext applicationContext = buildApplicationContext();

        final ProductService productService = applicationContext.getBean(ProductService.class);
        productService.displayProducts();

        System.out.println("-------------------------------------------------------------");

        // retrieving and using the lazy initialized product service
        final ProductService theLazyProductService = applicationContext.getBean("lazyProductService", ProductService.class);
        theLazyProductService.displayProducts();

        /*
         * Study:
         *      - what happens when we comment the @Primary annotation
         *      - how to retrieve and use the non-primary bean --> using its ID
         *      - the initialization of the @Lazy annotated bean, by retrieving the @Lazy ProductService
         */
    }

    private static ApplicationContext buildApplicationContext() {
        return USE_ANNOTATIONS_CONFIG ? new AnnotationConfigApplicationContext(BeanAttributesConfig.class) :
                new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
