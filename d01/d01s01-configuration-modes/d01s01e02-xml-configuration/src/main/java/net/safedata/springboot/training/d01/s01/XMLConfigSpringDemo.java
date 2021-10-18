package net.safedata.springboot.training.d01.s01;

import net.safedata.springboot.training.d01.s01.beans.HelloSpring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A first demo for the usage of a few simple Spring {@link org.springframework.context.annotation.Bean}s
 *
 * @author bogdan.solga
 */
public class XMLConfigSpringDemo {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        // retrieving a Spring bean by its class and using it
        final HelloSpring helloSpring = applicationContext.getBean(HelloSpring.class);
        helloSpring.displayWelcomeMessage();

        // retrieving a bean by its ID
        //System.out.println(applicationContext.getBean("helloSpringAsString"));
    }
}
