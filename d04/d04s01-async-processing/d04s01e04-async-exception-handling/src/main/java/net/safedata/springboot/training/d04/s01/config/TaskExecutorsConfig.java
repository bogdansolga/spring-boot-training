package net.safedata.springboot.training.d04.s01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TaskExecutorsConfig {

    private static final int PROCESSORS_COUNT = Runtime.getRuntime().availableProcessors();

    @Bean
    public ThreadPoolTaskExecutor defaultExecutor() {
        final ThreadPoolTaskExecutor defaultExecutor = new ThreadPoolTaskExecutor();

        defaultExecutor.setCorePoolSize(PROCESSORS_COUNT);
        defaultExecutor.setMaxPoolSize(PROCESSORS_COUNT * 2);
        defaultExecutor.setKeepAliveSeconds(60);
        defaultExecutor.setQueueCapacity(100);
        defaultExecutor.setThreadGroupName("async-executor-thread-pool-");
        defaultExecutor.setThreadNamePrefix("async-exec-thread-");
        defaultExecutor.setWaitForTasksToCompleteOnShutdown(true);
        defaultExecutor.setAwaitTerminationSeconds(60);
        defaultExecutor.setAllowCoreThreadTimeOut(true);
        defaultExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        defaultExecutor.initialize();

        return defaultExecutor;
    }
}
