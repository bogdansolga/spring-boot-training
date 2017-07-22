package net.safedata.springboot.training.d03.s01.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * A demo for the usage of a Spring {@link ConfigurationProperties} class
 *
 * @author bogdan.solga
 */
@ConfigurationProperties(prefix = "profiling")
@Configuration
public class ProfilingConfiguration {

    private String environment;

    private Connection connection;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public static class Connection {
        private int timeout;

        private int socketTimeout;

        private List<String> ips;

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        public int getSocketTimeout() {
            return socketTimeout;
        }

        public void setSocketTimeout(int socketTimeout) {
            this.socketTimeout = socketTimeout;
        }

        public List<String> getIps() {
            return ips;
        }

        public void setIps(List<String> ips) {
            this.ips = ips;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
