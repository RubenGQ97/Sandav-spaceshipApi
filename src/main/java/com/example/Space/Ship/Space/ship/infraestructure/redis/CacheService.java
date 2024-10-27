package com.example.Space.Ship.Space.ship.infraestructure.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);
    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void clearCaches() {
        try {
            cacheManager.getCache("spaceship").clear();
            cacheManager.getCache("spaceshipsByName").clear();
            cacheManager.getCache("allSpaceships").clear();
            logger.info("Caché limpiada exitosamente.");
        } catch (RedisConnectionFailureException ex) {
            logger.warn("No se pudo conectar a Redis para limpiar la caché: " + ex.getMessage());
        } catch (NullPointerException ex) {
            logger.warn("Algunas de las cachés especificadas no existen o no se pudieron limpiar.");
        }
    }
}
