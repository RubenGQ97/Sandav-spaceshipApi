package com.example.Space.Ship.Space.ship.infraestructure.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.stereotype.Component;

@Component
public class RedisErrorHandler implements CacheErrorHandler {



    private static final Logger logger = LoggerFactory.getLogger(RedisErrorHandler.class);

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        logger.warn("Error al obtener el valor de la cache usando la clave ({}). Exception: {}",cache.getName(),exception.getMessage());
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        logger.warn("Error al almacenar el valor de la cache usando la clave ({}). Exception: {}",cache.getName(),exception.getMessage());
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        logger.warn("Error al borrar el valor de la cache usando la clave ({}). Exception: {}",cache.getName(),exception.getMessage());
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        logger.warn("Error al limpiar la cache ({}). Exception: {}",cache.getName(),exception.getMessage());
    }
}
