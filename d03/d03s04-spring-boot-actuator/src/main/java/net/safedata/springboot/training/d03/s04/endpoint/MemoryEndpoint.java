package net.safedata.springboot.training.d03.s04.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

/**
 * A sample Spring Boot Actuator custom endpoint
 *
 * @author bogdan.solga
 */
@Component
@Endpoint(id = "memory")
public class MemoryEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryEndpoint.class);

    @ReadOperation
    public MemoryInfo memoryInfo() {
        final Runtime runtime = Runtime.getRuntime();
        final long freeMemory = runtime.freeMemory();

        return new MemoryInfo(runtime.totalMemory() - freeMemory, freeMemory);
    }

    @WriteOperation
    public String setASystemProperty(final String name, final String value) {
        LOGGER.info("Setting the property '{}' with the value '{}'", name, value);
        System.setProperty(name, value);

        return "All good";
    }
}
