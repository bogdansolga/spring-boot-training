package net.safedata.springboot.training.d01.s01;

import net.safedata.springboot.training.d01.s01.beans.HelloSpring;
import net.safedata.springboot.training.d01.s01.config.DemoConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A first demo for the usage of a few simple Spring {@link org.springframework.context.annotation.Bean}s, using
 * annotations based configuration
 *
 * @author bogdan.solga
 */
public class AnnotationConfigSpringDemo {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoConfig.class);

        // 'give me the bean (object) of type HelloSpring from the application context'
        final HelloSpring helloSpring = applicationContext.getBean("beanName", HelloSpring.class);
        helloSpring.displayWelcomeMessage();

        // 'give me the bean (object) of type String from the application context'
        //final String bean = applicationContext.getBean(String.class);
        //System.out.println(bean.toUpperCase());

        // retrieving a bean by it's ID
        //System.out.println(applicationContext.getBean("helloSpringAsString"));
    }
}
