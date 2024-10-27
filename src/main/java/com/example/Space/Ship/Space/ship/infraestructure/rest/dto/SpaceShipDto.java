package com.example.Space.Ship.Space.ship.infraestructure.rest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SpaceShipDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String model;
    private String manufacturer;
    private String film;
    private String urlImg;
}
