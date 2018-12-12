package net.safedata.springboot.training.d03.s04.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 * A sample Spring Boot Actuator endpoint
 *
 * @author bogdan.solga
 */
@Component
@Endpoint(id = "memory")
public class MemoryEndpoint {

    @ReadOperation
    public MemoryInfo memoryInfo() {
        final Runtime runtime = Runtime.getRuntime();
        final long freeMemory = runtime.freeMemory();

        return new MemoryInfo(runtime.totalMemory() - freeMemory, freeMemory);
    }
}
