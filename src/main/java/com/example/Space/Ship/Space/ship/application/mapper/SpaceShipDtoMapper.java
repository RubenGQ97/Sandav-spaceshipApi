package com.example.Space.Ship.Space.ship.application.mapper;

import com.example.Space.Ship.Space.ship.domain.model.SpaceShip;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.SpaceShipDto;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.request.SpaceShipRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpaceShipDtoMapper {

    SpaceShipDto toDto(SpaceShip spaceShip);
    SpaceShipDto toDto(SpaceShipRequest spaceShipRequest);
    SpaceShip toDomain(SpaceShipDto spaceShipDto);
    SpaceShip toDomain(SpaceShipRequest spaceShipRequest);
}
