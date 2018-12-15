package net.safedata.springboot.training.d01.s03;

import net.safedata.springboot.training.d01.s03.repository.ProductRepository;
import net.safedata.springboot.training.d01.s03.config.BeanAliasingConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A simple demo of a Spring project to demo the {@link org.springframework.context.annotation.Bean} aliasing
 *
 * @author bogdan.solga
 */
public class BeanAliasingDemo {

    private static final boolean USE_ANNOTATIONS_CONFIG = true;

    public static void main(String[] args) {
        final ApplicationContext applicationContext = buildApplicationContext();

        final ProductRepository productRepository = applicationContext.getBean("prodRepo", ProductRepository.class);
        productRepository.displayProducts();
    }

    private static ApplicationContext buildApplicationContext() {
        return USE_ANNOTATIONS_CONFIG ? new AnnotationConfigApplicationContext(BeanAliasingConfig.class) :
                new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
