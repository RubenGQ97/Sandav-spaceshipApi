package com.example.Space.Ship.Space.ship.infraestructure.rest;

import com.example.Space.Ship.Space.ship.infraestructure.adapter.exception.ErrorReponse;
import com.example.Space.Ship.Space.ship.infraestructure.adapter.exception.SpaceShipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SpaceShipException.class)
    public ResponseEntity<?> handleSpaceShipException(SpaceShipException ex) {
        var error =  new ErrorReponse(ex.getCode().value(), ex.getMessage());
        return new ResponseEntity<>(error,ex.getCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        var error = new ErrorReponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Se ha producido un error inesperado en el servidor.");
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
