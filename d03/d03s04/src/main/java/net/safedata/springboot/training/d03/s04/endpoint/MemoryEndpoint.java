package net.safedata.springboot.training.d03.s04.endpoint;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

/**
 * A sample Spring Boot Actuator endpoint
 *
 * @author bogdan.solga
 */
@Component
public class MemoryEndpoint implements Endpoint<MemoryInfo> {

    @Override
    public String getId() {
        return "memory";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public MemoryInfo invoke() {
        final Runtime runtime = Runtime.getRuntime();
        final long freeMemory = runtime.freeMemory();

        return new MemoryInfo(runtime.totalMemory() - freeMemory, freeMemory);
    }
}
