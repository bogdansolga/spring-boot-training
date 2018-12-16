package net.safedata.spring.training.complete.project.config;

import net.safedata.spring.training.complete.project.aop.aspect.LoggingAspect;
import net.safedata.spring.training.complete.project.aop.aspect.ProfilingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectJConfig {

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

    @Bean
    public ProfilingAspect profiler() {
        return new ProfilingAspect();
    }
}
