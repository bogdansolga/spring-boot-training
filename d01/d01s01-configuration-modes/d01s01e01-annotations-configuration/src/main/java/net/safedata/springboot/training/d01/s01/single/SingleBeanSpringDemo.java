package net.safedata.springboot.training.d01.s01.single;

import net.safedata.springboot.training.d01.s01.single.beans.HelloSpring;
import net.safedata.springboot.training.d01.s01.single.config.SingleBeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A first demo for the usage of a simple Spring {@link org.springframework.context.annotation.Bean}, using
 * annotations based configuration
 *
 * @author bogdan.solga
 */
public class SingleBeanSpringDemo {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SingleBeanConfig.class);

        // 'give me the bean (object) of type HelloSpring from the application context'
        final HelloSpring helloSpring = applicationContext.getBean(HelloSpring.class);
        helloSpring.displayWelcomeMessage();
    }
}
