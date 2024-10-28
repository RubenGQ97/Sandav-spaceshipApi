package com.example.Space.Ship.Space.ship.infraestructure.adapter.jpa;

import com.example.Space.Ship.Space.ship.domain.model.SpaceShip;
import com.example.Space.Ship.Space.ship.domain.port.SpaceShipJpaPort;
import com.example.Space.Ship.Space.ship.infraestructure.adapter.exception.SpaceShipException;
import com.example.Space.Ship.Space.ship.infraestructure.adapter.mapper.SpaceShipMapper;
import com.example.Space.Ship.Space.ship.infraestructure.adapter.repository.SpaceShipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.Space.Ship.Space.ship.domain.model.constant.SpaceShipCostant.SPACESHIP_ALREADY_EXISTS;
import static com.example.Space.Ship.Space.ship.domain.model.constant.SpaceShipCostant.SPACESHIP_NOT_FOUND_ERROR;


@Service
@Transactional
public class SpaceShipJpaAdapter implements SpaceShipJpaPort {

    private final SpaceShipRepository spaceShipRepository;
    private final SpaceShipMapper spaceShipMapper;

    public SpaceShipJpaAdapter(SpaceShipRepository spaceShipRepository, SpaceShipMapper spaceShipMapper) {
        this.spaceShipRepository = spaceShipRepository;
        this.spaceShipMapper = spaceShipMapper;
    }


    @Override
    public Page<SpaceShip> getAllShips(Pageable pageable) {
        var shipEntities = spaceShipRepository.findAll(pageable);

        return shipEntities.map(spaceShipMapper::toDomain);
    }

    @Override
    public SpaceShip getSpaceShipById(int id) {
        var ship = spaceShipRepository.findById(id);
        return spaceShipMapper.toDomain(ship.
                orElseThrow(()->
                        new SpaceShipException(HttpStatus.NOT_FOUND,SPACESHIP_NOT_FOUND_ERROR + id)));
    }

    @Override
    public List<SpaceShip> findShipsByName(String name) {
        var entitiesList = spaceShipRepository.findByNameContaining(name);

        var shiptList =entitiesList.get().stream().map(spaceShipMapper::toDomain).collect(Collectors.toList());
        return shiptList;
    }

    @Override
    public SpaceShip createSpaceShip(SpaceShip spaceShip) {

        if (spaceShipRepository.existsByName(spaceShip.getName())){
            throw new SpaceShipException(HttpStatus.CONFLICT,SPACESHIP_ALREADY_EXISTS);
        }
        var newship= spaceShipRepository.save(spaceShipMapper.toEntity(spaceShip));
        return spaceShipMapper.toDomain(newship);

    }


    @Override
    public SpaceShip updateSpaceShip(SpaceShip spaceShip) {
        var shipToUpdate= spaceShipMapper.toEntity(spaceShip);

        return spaceShipRepository.findById(shipToUpdate.getId()).map(
                spaceShipEntity -> {
                    var shipUpdated = spaceShipRepository.save(shipToUpdate);
                    return spaceShipMapper.toDomain(shipUpdated);
                }).orElseThrow(() ->
                    new SpaceShipException(HttpStatus.NOT_FOUND,SPACESHIP_NOT_FOUND_ERROR + shipToUpdate.getId())
        );
    }

    @Override
    public Boolean deleteSpaceShip(int id) {

        return spaceShipRepository.findById(id).map(
                spaceShipEntity -> {
                    spaceShipRepository.deleteById(id);
                    return !spaceShipRepository.existsById(id);
                }).orElseThrow(() ->
                new SpaceShipException(HttpStatus.NOT_FOUND,SPACESHIP_NOT_FOUND_ERROR + id)
        );
    }

}
