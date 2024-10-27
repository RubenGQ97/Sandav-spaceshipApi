package com.example.Space.Ship.Space.ship.infraestructure.adapter.repository;

import com.example.Space.Ship.Space.ship.infraestructure.adapter.entity.SpaceShipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpaceShipRepository extends JpaRepository<SpaceShipEntity,Integer> {
    Page<SpaceShipEntity> findAll(Pageable pageable);
    Optional<SpaceShipEntity> findById(int id);
    Optional<List<SpaceShipEntity>> findByNameContaining(String name);
    boolean existsByName(String name);
}
