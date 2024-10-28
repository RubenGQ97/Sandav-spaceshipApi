package com.example.Space.Ship.Space.ship.infraestructure.adapter.mapper;

import com.example.Space.Ship.Space.ship.domain.model.SpaceShip;
import com.example.Space.Ship.Space.ship.infraestructure.adapter.entity.SpaceShipEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpaceShipMapper {
    SpaceShip toDomain(SpaceShipEntity spaceShipEntity);
    SpaceShipEntity toEntity(SpaceShip spaceShip);
}
