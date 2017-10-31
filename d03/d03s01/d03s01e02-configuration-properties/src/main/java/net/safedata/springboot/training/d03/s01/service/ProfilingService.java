package net.safedata.springboot.training.d03.s01.service;

import net.safedata.springboot.training.d03.s01.config.ProfilingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ProfilingService {

    private final ProfilingConfiguration profilingConfiguration;

    @Value("${profiling.environment}")
    private String profilingEnvironment;

    @Value("${profiling.connection.timeout}")
    private String profilingConnectionTimeout;

    @Value("${profiling.connection.socket-timeout}")
    private String connectionSocketTimeout;

    @Autowired
    public ProfilingService(final ProfilingConfiguration profilingConfiguration) {
        this.profilingConfiguration = profilingConfiguration;
    }

    @PostConstruct
    public void displayProperties() {
        System.out.println("Profiling environment - " + profilingEnvironment);
        System.out.println("Profiling connection timeout - " + profilingConnectionTimeout);
        System.out.println("Connection socket timeout - " + connectionSocketTimeout);

        System.out.println(profilingConfiguration.getEnvironment());
        System.out.println(profilingConfiguration.getConnection().getSocketTimeout());
        System.out.println(profilingConfiguration.getConnection().getIps());
    }
}
