package net.safedata.springboot.training.d04.s01;

import net.safedata.springboot.training.d04.s01.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * A Spring Boot class which demos the usage of {@link org.springframework.core.task.TaskExecutor}s
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class TaskExecutorsDemo implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... strings) throws Exception {
        productService.voidAsyncCall();
        productService.getFuture();
    }

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext =
                new SpringApplication(TaskExecutorsDemo.class).run(args);
        applicationContext.close();
    }
}
