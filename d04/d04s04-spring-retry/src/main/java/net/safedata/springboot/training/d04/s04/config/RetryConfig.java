package net.safedata.springboot.training.d04.s04.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.resilience.annotation.EnableResilientMethods;

@Configuration
@EnableResilientMethods
public class RetryConfig {
}
