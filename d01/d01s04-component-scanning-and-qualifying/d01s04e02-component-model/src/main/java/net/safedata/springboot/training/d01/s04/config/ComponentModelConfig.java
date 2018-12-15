package net.safedata.springboot.training.d01.s04.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.safedata.springboot.training.d01.s04.repository.ProductRepository;
import net.safedata.springboot.training.d01.s04.service.ProductService;

/**
 * A simple Spring configuration, which wires the beans automatically via {@link ComponentScan}ing
 *
 * @author bogdan.solga
 */
@Configuration
@ComponentScan(basePackages = "net.safedata.springboot.training.d01.s04")
public class ComponentModelConfig {
	
	@Bean(autowire = Autowire.BY_TYPE)
	public ProductService anotherProductService() {
		return new ProductService(new ProductRepository());
	}
}
