package net.safedata.springboot.training.d01.s05.service;

import net.safedata.springboot.training.d01.s05.config.Profiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * A simple product {@link Service}, which uses the {@link Environment} as an {@link Autowired} collaborator
 *
 * @author bogdan.solga
 */
@Service
public class ProductService {

    // the current environment can be easily autowired
    private final Environment environment;

    @Autowired
    public ProductService(final Environment environment) {
        this.environment = environment;
    }

    public void displayProducts() {
        if (environment.acceptsProfiles(Profiles.PROD)) {
            System.out.println("Running with the '" + Profiles.PROD + "' profile...");
        }

        System.out.println("Displaying the products for the '" + Arrays.asList(environment.getActiveProfiles()) + "' profiles...");

        // resolved per active profile
        final String remoteEndpoint = environment.getProperty("remote.endpoint.url");
        System.out.println("The configured endpoint is " + remoteEndpoint);
    }
}
