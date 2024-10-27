package com.example.Space.Ship.Space.ship.infraestructure.adapter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpaceShipException extends RuntimeException{

    private HttpStatus code;
    private String message;
}
