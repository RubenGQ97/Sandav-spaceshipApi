package com.example.Space.Ship.Space.ship.infraestructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SpaceShipRequest {
    @NotBlank(message = "The name of the spaceship is required")
    @Size(min = 1, max = 50, message = "The name must be between 1 and 50 characters")
    @NotNull
    private String name;

    @Size(min = 1, max = 50, message = "The name must be between 1 and 50 characters")
    private String model;

    @Size(min = 1, max = 50, message = "The name must be between 1 and 50 characters")
    private String manufacturer;

    @NotBlank(message = "The film of the spaceship is required")
    @Size(min = 1, max = 50, message = "The name must be between 1 and 50 characters")
    @NotNull(message = "couldnt be null")
    private String film;

    @Override
    public String toString() {
        return "SpaceShipRequest{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", film='" + film + '\'' +
                '}';
    }
}
