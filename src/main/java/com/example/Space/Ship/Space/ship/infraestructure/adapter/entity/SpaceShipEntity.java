package com.example.Space.Ship.Space.ship.infraestructure.adapter.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SPACESHIP")
@Data
public class SpaceShipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "spaceship_seq")
    @SequenceGenerator(name = "spaceship_seq", sequenceName = "spaceship_sequence", allocationSize = 1, initialValue = 11)
    @Column(name = "id")
    private int id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "film",nullable = false)
    private String film;

    @Column(name = "url_img",unique = true)
    private String urlImg;

}
