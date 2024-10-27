package com.example.Space.Ship.Space.ship.infraestructure.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedisConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public CacheErrorHandler cacheErrorHandler() {
        logger.info("Registrando RedisCacheErrorHandler como manejador de errores de caché");
        return new RedisErrorHandler();
    }
}
