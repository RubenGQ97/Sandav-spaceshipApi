package com.example.Space.Ship.Space.ship.infraestructure.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NegativeIdAspect {
    private static final Logger logger = LoggerFactory.getLogger(NegativeIdAspect.class);

    @Before("execution(* com.example.Space.Ship.Space.ship.application.service.SpaceShipService.getSpaceShipById(..)) && args(id)")
    public void logNegativeId(int id) {
        if (id < 0) {
            logger.info("Se ha realizado una busqueda de una nave con usando un ID negativo: " + id);
        }
    }

}
