package com.example.Space.Ship.Space.ship.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpaceShip {
    private int id;
    private String name;
    private String model;
    private String manufacturer;
    private String film;
    private String urlImg;
}