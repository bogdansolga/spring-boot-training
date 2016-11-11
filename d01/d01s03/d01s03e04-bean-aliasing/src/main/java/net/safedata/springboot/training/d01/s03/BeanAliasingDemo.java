package net.safedata.springboot.training.d01.s03;

import net.safedata.springboot.training.d01.s03.repository.ProductRepository;
import net.safedata.springboot.training.d01.s03.config.BeanAliasingConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A simple demo of a Spring project to demo the {@link org.springframework.context.annotation.Bean} aliasing
 *
 * @author bogdan.solga
 */
public class BeanAliasingDemo {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanAliasingConfig.class);

        final ProductRepository productRepository = applicationContext.getBean("prodRepo", ProductRepository.class);
        productRepository.displayProducts();
    }
}
