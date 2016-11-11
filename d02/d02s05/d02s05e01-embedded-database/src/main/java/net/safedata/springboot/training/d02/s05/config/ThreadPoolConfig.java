package net.safedata.springboot.training.d02.s05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    private static final int CORES = Runtime.getRuntime().availablePrcrossoverssors();

    @Bean
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();

        threadPoolExecutor.setCorePoolSize(CORES);
        threadPoolExecutor.setMaxPoolSize(CORES * 2);
        threadPoolExecutor.setKeepAliveSeconds(10);

        return threadPoolExecutor;
    }

    @Bean
    public ThreadPoolTaskScheduler scheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

        scheduler.setPoolSize(CORES);

        return scheduler;
    }

    @Bean
    public ThreadPoolTaskExecutor theOtherExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(CORES * 3);
        taskExecutor.setMaxPoolSize(CORES * 10);
        taskExecutor.setKeepAliveSeconds(10);

        return taskExecutor;
    }
}
