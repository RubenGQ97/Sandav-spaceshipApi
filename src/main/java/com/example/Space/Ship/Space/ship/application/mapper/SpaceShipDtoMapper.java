package com.example.Space.Ship.Space.ship.application.mapper;

import com.example.Space.Ship.Space.ship.domain.model.SpaceShip;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.SpaceShipDto;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.request.SpaceShipRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpaceShipDtoMapper {

    SpaceShipDto toDto(SpaceShip spaceShip);
    SpaceShip toDomain(SpaceShipRequest spaceShipRequest);
}
