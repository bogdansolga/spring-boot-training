package net.safedata.springboot.training.d01.s05.service;

import net.safedata.spring.training.domain.bootstrap.ProductsSetup;
import net.safedata.spring.training.domain.model.Product;
import net.safedata.springboot.training.d01.s05.RunProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * A simple product {@link Service}, which uses the {@link Environment} as an {@link Autowired} collaborator
 *
 * @author bogdan.solga
 */
@Service
public class ProductService {

    // the current environment can be easily autowired
    private final Environment environment;

    private final List<Product> products = ProductsSetup.getRandomProducts();

    @Autowired
    public ProductService(final Environment environment) {
        this.environment = environment;
    }

    public void displayProducts() {
        if (environment.acceptsProfiles(Profiles.of(RunProfiles.PROD))) {
            System.out.println("Running with the '" + RunProfiles.PROD + "' profile...");
        }

        // resolved per active profile
        final String remoteEndpoint = environment.getProperty("remote.endpoint.url");
        System.out.println("The configured endpoint is " + remoteEndpoint);

        System.out.println();
        System.out.println("Displaying the products for the '" + Arrays.asList(environment.getActiveProfiles()) + "' profiles...");
        products.forEach(product -> System.out.println("\t" + product));
    }
}
