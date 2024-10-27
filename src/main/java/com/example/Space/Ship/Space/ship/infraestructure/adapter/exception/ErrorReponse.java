package com.example.Space.Ship.Space.ship.infraestructure.adapter.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorReponse {

    private int statusCode;
    private String message;
}
