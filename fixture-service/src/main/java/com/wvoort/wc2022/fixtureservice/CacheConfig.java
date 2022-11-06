package com.wvoort.wc2022.fixtureservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.management.timer.Timer;
import java.time.Instant;
import java.util.Arrays;

@Slf4j
@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    private static final String FIXTURES = "fixtures";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(FIXTURES)));
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {FIXTURES})
    @Scheduled(fixedDelay = Timer.ONE_MINUTE * 15)
    public void reportCacheEvict() {
        log.info("Fixtures cache flushed at {}", Instant.now());
    }

}
