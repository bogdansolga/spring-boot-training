package net.safedata.spring.boot.training.solace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    private static final int PROCESSORS_COUNT = Runtime.getRuntime().availableProcessors();

    @Primary
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        final ThreadPoolTaskExecutor shortLivedTaskExecutor = new ThreadPoolTaskExecutor();

        shortLivedTaskExecutor.setCorePoolSize(PROCESSORS_COUNT / 2);
        shortLivedTaskExecutor.setMaxPoolSize(PROCESSORS_COUNT);
        shortLivedTaskExecutor.setKeepAliveSeconds(3);
        shortLivedTaskExecutor.setQueueCapacity(1000);
        shortLivedTaskExecutor.setThreadGroupName("product-listener-thread-pool-");
        shortLivedTaskExecutor.setThreadNamePrefix("product-listener-thread-");
        shortLivedTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        shortLivedTaskExecutor.setAwaitTerminationSeconds(5);
        shortLivedTaskExecutor.setAllowCoreThreadTimeOut(true);
        shortLivedTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        shortLivedTaskExecutor.initialize();

        return shortLivedTaskExecutor;
    }
}
