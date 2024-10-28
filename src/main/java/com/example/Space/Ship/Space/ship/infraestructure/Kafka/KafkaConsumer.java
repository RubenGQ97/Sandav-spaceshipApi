package com.example.Space.Ship.Space.ship.infraestructure.Kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);


    @KafkaListener(topics = "space-ship-topic", groupId = "space-ship-group")
    public void listenGroupSpaceShip(String message) {
        logger.info("Se ha recibido desde kafka el siguiente mensaje: "+message);
    }
}
