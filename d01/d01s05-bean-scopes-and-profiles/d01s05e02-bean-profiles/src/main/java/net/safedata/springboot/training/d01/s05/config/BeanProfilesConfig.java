package net.safedata.springboot.training.d01.s05.config;

import net.safedata.springboot.training.d01.s05.repository.ProductRepository;
import net.safedata.springboot.training.d01.s05.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * A Spring configuration which wires several {@link Bean}s according to the used {@link Profile}
 *
 * @author bogdan.solga
 */
@Configuration
public class BeanProfilesConfig {

    @Bean
    @Profile(Profiles.DEV)
    public ProductService devProductService() {
        return new ProductService(devProductRepository());
    }

    @Bean
    @Profile(Profiles.TOMCAT)
    public String tomcat() {
        return "Running with Tomcat";
    }

    @Bean
    @Profile(Profiles.PROD)
    public ProductService prodProductService() {
        return new ProductService(prodProductRepository());
    }

    @Bean
    @Profile(Profiles.DEFAULT)
    public ProductService defaultProductService() {
        return new ProductService(prodProductRepository());
    }

    @Bean
    @Profile(Profiles.DEV)
    public FileSavingServiceExample devFileSavingServiceExample() {
        return new FileSavingServiceExample(new DevFileSavingService());
    }

    @Bean
    @Profile(Profiles.PROD)
    public FileSavingServiceExample prodFileSavingServiceExample() {
        return new FileSavingServiceExample(new ProdFileSavingService());
    }

    @Bean
    public ProductRepository devProductRepository() {
        return new ProductRepository("H2");
    }

    @Bean
    public ProductRepository prodProductRepository() {
        return new ProductRepository("PostgreSQL");
    }
}
