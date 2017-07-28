package net.safedata.springboot.training.d04.s02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TaskExecutorConfig {

    private static final int PROCESSORS_COUNT = Runtime.getRuntime().availableProcessors();

    @Primary
    @Bean
    public ThreadPoolTaskExecutor shortLivedTasksExecutor() {
        final ThreadPoolTaskExecutor shortLivedTaskExecutor = new ThreadPoolTaskExecutor();

        shortLivedTaskExecutor.setCorePoolSize(PROCESSORS_COUNT);
        shortLivedTaskExecutor.setMaxPoolSize(PROCESSORS_COUNT * 2);
        shortLivedTaskExecutor.setKeepAliveSeconds(20);
        shortLivedTaskExecutor.setQueueCapacity(100);
        shortLivedTaskExecutor.setThreadGroupName("quarts-executor-thread-pool-");
        shortLivedTaskExecutor.setThreadNamePrefix("quartz-thread-");
        shortLivedTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        shortLivedTaskExecutor.setAwaitTerminationSeconds(5);
        shortLivedTaskExecutor.setAllowCoreThreadTimeOut(true);
        shortLivedTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        shortLivedTaskExecutor.initialize();

        return shortLivedTaskExecutor;
    }
}
