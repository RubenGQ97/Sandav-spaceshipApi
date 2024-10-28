package com.example.Space.Ship.Space.ship.domain.port;

import com.example.Space.Ship.Space.ship.domain.model.SpaceShip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpaceShipJpaPort {
    Page<SpaceShip> getAllShips(Pageable pageable);
    SpaceShip getSpaceShipById(int i);
    List<SpaceShip> findShipsByName(String name);
    SpaceShip createSpaceShip(SpaceShip spaceShip);
    SpaceShip updateSpaceShip(SpaceShip spaceShip);
    Boolean deleteSpaceShip(int id);
}
