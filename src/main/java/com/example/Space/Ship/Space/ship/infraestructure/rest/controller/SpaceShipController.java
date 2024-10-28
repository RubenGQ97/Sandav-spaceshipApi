package com.example.Space.Ship.Space.ship.infraestructure.rest.controller;


import com.example.Space.Ship.Space.ship.application.service.SpaceShipService;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.SpaceShipDto;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.request.SpaceShipRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.Space.Ship.Space.ship.domain.model.constant.SpaceShipCostant.SPACESHIP_DELETED;
import static com.example.Space.Ship.Space.ship.domain.model.constant.SpaceShipCostant.SPACESHIP_NOT_FOUND_ERROR;

@RestController
@RequestMapping("api/spaceship")
public class SpaceShipController {
    private final SpaceShipService spaceShipService;

    public SpaceShipController(SpaceShipService spaceShipService) {
        this.spaceShipService = spaceShipService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<SpaceShipDto>> getAllShips(
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        return ResponseEntity.ok(spaceShipService.getAllShips(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceShipDto> getSpaceShipById(@PathVariable int id) {
        return ResponseEntity.ok(spaceShipService.getSpaceShipById(id));
    }

    @GetMapping("/search")
    public List<SpaceShipDto> findShipsByName(@RequestParam String name) {
        return spaceShipService.findShipsByName(name);
    }

    @PostMapping("create")
    public ResponseEntity<SpaceShipDto> createSpaceShip(@Valid @RequestBody SpaceShipRequest spaceShipRequest) {
        return ResponseEntity.ok(spaceShipService.createSpaceShip(spaceShipRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceShipDto> updateSpaceShip(@PathVariable int id, @Valid @RequestBody SpaceShipRequest spaceShipRequest) {
        return ResponseEntity.ok(spaceShipService.updateSpaceShip(id, spaceShipRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpaceShip(@PathVariable int id) {
        return spaceShipService.deleteSpaceShip(id) ? ResponseEntity.ok(SPACESHIP_DELETED): ResponseEntity.ok(SPACESHIP_NOT_FOUND_ERROR);
    }
}
