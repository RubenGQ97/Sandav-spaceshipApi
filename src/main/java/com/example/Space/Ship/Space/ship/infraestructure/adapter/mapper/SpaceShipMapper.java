package com.example.Space.Ship.Space.ship.infraestructure.adapter.mapper;

import com.example.Space.Ship.Space.ship.domain.model.SpaceShip;
import com.example.Space.Ship.Space.ship.infraestructure.adapter.entity.SpaceShipEntity;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.request.SpaceShipRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpaceShipMapper {
    SpaceShip toDomain(SpaceShipEntity spaceShipEntity);
    SpaceShip toDomain(SpaceShipRequest spaceShipRequest);
    SpaceShipEntity toEntity(SpaceShip spaceShip);
    SpaceShipEntity toEntity(SpaceShipRequest spaceShipRequest);
}
