package com.example.Space.Ship.Space.ship.application.usecases;

import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.SpaceShipDto;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.request.SpaceShipRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface SpaceShipUseCases {
    Page<SpaceShipDto> getAllShips(Pageable pageable);
    SpaceShipDto getSpaceShipById(int i);
    List<SpaceShipDto> findShipsByName(String name);
    SpaceShipDto createSpaceShip(SpaceShipRequest spaceShipRequest);
    SpaceShipDto updateSpaceShip(int id, SpaceShipRequest spaceShipRequest);
    Boolean deleteSpaceShip(int id);
}
