package net.safedata.springboot.training.d04.s01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TaskExecutorsConfig {

    private static final int PROCESSORS_COUNT = Runtime.getRuntime().availableProcessors();

    @Primary
    @Bean
    public ThreadPoolTaskExecutor longLivedTaskExecutor() {
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        threadPoolTaskExecutor.setCorePoolSize(PROCESSORS_COUNT / 2);
        threadPoolTaskExecutor.setMaxPoolSize(PROCESSORS_COUNT * 2);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);

        // how many tasks can be queued before being rejected
        threadPoolTaskExecutor.setQueueCapacity(1000);

        // the rejection policy for the executor
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolTaskExecutor.setThreadGroupName("executor-thread-pool-");
        threadPoolTaskExecutor.setThreadNamePrefix("long-lived-exec-thread-");
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(20);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);

        threadPoolTaskExecutor.initialize();

        return threadPoolTaskExecutor;
    }

    @Bean
    public ThreadPoolTaskExecutor shortLivedTasksExecutor() {
        final ThreadPoolTaskExecutor shortLivedTaskExecutor = new ThreadPoolTaskExecutor();

        shortLivedTaskExecutor.setCorePoolSize(PROCESSORS_COUNT / 2);
        shortLivedTaskExecutor.setMaxPoolSize(PROCESSORS_COUNT * 2);
        shortLivedTaskExecutor.setKeepAliveSeconds(10);
        shortLivedTaskExecutor.setQueueCapacity(100);
        shortLivedTaskExecutor.setThreadGroupName("short-lived-executor-thread-pool-");
        shortLivedTaskExecutor.setThreadNamePrefix("short-lived-exec-thread-");
        shortLivedTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        shortLivedTaskExecutor.setAwaitTerminationSeconds(5);
        shortLivedTaskExecutor.setAllowCoreThreadTimeOut(true);
        shortLivedTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        shortLivedTaskExecutor.initialize();

        return shortLivedTaskExecutor;
    }
}
