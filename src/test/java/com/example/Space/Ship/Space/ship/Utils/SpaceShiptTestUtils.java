package com.example.Space.Ship.Space.ship.Utils;

import com.example.Space.Ship.Space.ship.domain.model.SpaceShip;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.SpaceShipDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpaceShiptTestUtils {


    public List<SpaceShipDto> generateSpaceShipsDto(int numberOfShips) {
        return IntStream.rangeClosed(1, numberOfShips)
                .mapToObj(i -> {
                    SpaceShipDto spaceShip = new SpaceShipDto();
                    spaceShip.setId(i);
                    spaceShip.setName("SpaceShip" + i);
                    spaceShip.setModel("Model");
                    spaceShip.setManufacturer("Manufacturer");
                    spaceShip.setFilm("Star Wars");
                    spaceShip.setUrlImg("https://example.com/spaceship.jpg"+i);
                    return spaceShip;
                })
                .collect(Collectors.toList());
    }

    public List<SpaceShip> generateSpaceShips(int numberOfShips) {
        return IntStream.rangeClosed(1, numberOfShips)
                .mapToObj(i -> {
                    SpaceShip spaceShip = new SpaceShip();
                    spaceShip.setId(i);
                    spaceShip.setName("SpaceShip" + i);
                    spaceShip.setModel("Model");
                    spaceShip.setManufacturer("Manufacturer");
                    spaceShip.setFilm("Star Wars");
                    spaceShip.setUrlImg("https://example.com/spaceship.jpg"+i);
                    return spaceShip;
                })
                .collect(Collectors.toList());
    }
}
