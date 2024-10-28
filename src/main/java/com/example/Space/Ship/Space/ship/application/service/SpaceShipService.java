package com.example.Space.Ship.Space.ship.application.service;

import com.example.Space.Ship.Space.ship.application.mapper.SpaceShipDtoMapper;
import com.example.Space.Ship.Space.ship.application.usecases.SpaceShipUseCases;
import com.example.Space.Ship.Space.ship.domain.port.SpaceShipJpaPort;
import com.example.Space.Ship.Space.ship.infraestructure.Kafka.KafkaProducer;
import com.example.Space.Ship.Space.ship.infraestructure.redis.CacheService;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.SpaceShipDto;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.request.SpaceShipRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceShipService implements SpaceShipUseCases {

    private final SpaceShipJpaPort spaceShipJpaPort;
    private final SpaceShipDtoMapper spaceShipDtoMapper;
    private final CacheService cacheService;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public SpaceShipService(SpaceShipJpaPort spaceShipJpaPort, SpaceShipDtoMapper spaceShipDtoMapper, CacheService cacheService, KafkaProducer kafkaProducer) {
        this.spaceShipJpaPort = spaceShipJpaPort;
        this.spaceShipDtoMapper = spaceShipDtoMapper;
        this.cacheService = cacheService;
        this.kafkaProducer = kafkaProducer;
    }


    @Override
    @Cacheable(value = "allSpaceships", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    @CircuitBreaker(name = "redisCircuitBreaker", fallbackMethod = "getAllShipsFallBack")
    public Page<SpaceShipDto> getAllShips(Pageable pageable) {
        var shipList = spaceShipJpaPort.getAllShips(pageable);

        return shipList.map(spaceShipDtoMapper::toDto);
    }

    @Override
    @Cacheable(value = "spaceship", key = "#id")
    @CircuitBreaker(name = "redisCircuitBreaker", fallbackMethod = "getSpaceShipByIdFallBack")
    public SpaceShipDto getSpaceShipById(int id) {
        var ship = spaceShipJpaPort.getSpaceShipById(id);

        return spaceShipDtoMapper.toDto(ship);
    }

    @Override
    @Cacheable(value = "spaceshipsByName", key = "#name")
    @CircuitBreaker(name = "redisCircuitBreaker", fallbackMethod = "findShipsByNameFallBack")
    public List<SpaceShipDto> findShipsByName(String name) {
        var shipList = spaceShipJpaPort.findShipsByName(name);

        var shipListDto = shipList.stream().map(spaceShipDtoMapper::toDto).collect(Collectors.toList());

        return shipListDto;
    }

    @Override
    public SpaceShipDto createSpaceShip(SpaceShipRequest spaceShipRequest) {
        var shipToCreate = spaceShipDtoMapper.toDomain(spaceShipRequest);
        var shipCreated = spaceShipJpaPort.createSpaceShip(shipToCreate);
        cacheService.clearCaches();
        kafkaProducer.sendMessage("Se ha creado la nave: "+shipCreated.toString());
        return spaceShipDtoMapper.toDto(shipCreated);
    }

    @Override
    public SpaceShipDto updateSpaceShip(int id, SpaceShipRequest spaceShipRequest) {
        var shipToUpdate = spaceShipDtoMapper.toDomain(spaceShipRequest);
        shipToUpdate.setId(id);
        var shipUpdated = spaceShipJpaPort.updateSpaceShip(shipToUpdate);
        cacheService.clearCaches();
        kafkaProducer.sendMessage("Se ha actualizado la nave: { "+shipToUpdate+" } Ahora su valor es: "+shipUpdated.toString());
        return spaceShipDtoMapper.toDto(shipUpdated);
    }

    @Override
    public Boolean deleteSpaceShip(int id) {
        var isDeleted = spaceShipJpaPort.deleteSpaceShip(id);
        cacheService.clearCaches();
        kafkaProducer.sendMessage("Se ha eliminado la nave con el id: "+id);
        return isDeleted;
    }



    public Page<SpaceShipDto> getAllShipsFallBack(Pageable pageable, Throwable t) {
        var shipList = spaceShipJpaPort.getAllShips(pageable);
        return shipList.map(spaceShipDtoMapper::toDto);
    }


    public SpaceShipDto getSpaceShipByIdFallBack(int id, Throwable t) {
        var ship = spaceShipJpaPort.getSpaceShipById(id);
        return spaceShipDtoMapper.toDto(ship);
    }


    public List<SpaceShipDto> findShipsByNameFallBack(String name, Throwable t) {
        var shipList = spaceShipJpaPort.findShipsByName(name);
        var shipListDto = shipList.stream().map(spaceShipDtoMapper::toDto).collect(Collectors.toList());
        return shipListDto;
    }

}
