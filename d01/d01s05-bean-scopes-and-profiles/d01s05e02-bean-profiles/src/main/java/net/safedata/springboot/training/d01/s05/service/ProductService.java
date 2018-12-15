package net.safedata.springboot.training.d01.s05.service;

import net.safedata.springboot.training.d01.s05.RunProfiles;
import net.safedata.springboot.training.d01.s05.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * A simple {@link ProductService}, which uses the {@link ProductRepository} as a manually wired collaborator
 *
 * @author bogdan.solga
 */
public class ProductService {

    @Autowired
    private Environment environment;

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void displayProfiles() {
        System.out.println("Running with the '" + Arrays.asList(environment.getActiveProfiles()) + "' profiles...");
        System.out.println("Default profiles: '" + Arrays.asList(environment.getDefaultProfiles()) + "'");
    }

    public void displayProducts() {
        if (environment.acceptsProfiles(Profiles.of(RunProfiles.PROD))) {
            System.out.println("Do something only when prod is enabled...");
        }

        productRepository.displayProducts();
    }
}
