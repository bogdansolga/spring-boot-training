package net.safedata.springboot.training.d03s03.config;

import net.safedata.springboot.training.d03s03.aspect.LoggingAspect;
import net.safedata.springboot.training.d03s03.aspect.profiling.Profiler;
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
    public Profiler profiler() {
        return new Profiler();
    }
}
