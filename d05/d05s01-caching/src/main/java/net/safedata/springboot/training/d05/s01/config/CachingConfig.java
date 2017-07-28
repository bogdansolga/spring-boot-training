package net.safedata.springboot.training.d05.s01.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public CacheManager cacheManager() {
        final SimpleCacheManager simpleCacheManager = new SimpleCacheManager();

        final Cache cache = new ConcurrentMapCache(CacheNames.PRODUCTS);
        simpleCacheManager.setCaches(Collections.singleton(cache));

        return simpleCacheManager;

        //return new ConcurrentMapCacheManager();
    }
}
