package com.example.Space.Ship.Space.ship.service;

import com.example.Space.Ship.Space.ship.Utils.SpaceShiptTestUtils;
import com.example.Space.Ship.Space.ship.application.mapper.SpaceShipDtoMapper;
import com.example.Space.Ship.Space.ship.application.service.SpaceShipService;
import com.example.Space.Ship.Space.ship.domain.model.SpaceShip;
import com.example.Space.Ship.Space.ship.domain.port.SpaceShipJpaPort;
import com.example.Space.Ship.Space.ship.infraestructure.redis.CacheService;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.SpaceShipDto;
import com.example.Space.Ship.Space.ship.infraestructure.rest.dto.request.SpaceShipRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.RedisConnectionFailureException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class SpaceShipServiceTest {

    @MockBean
    private SpaceShipJpaPort spaceShipJpaPort;

    @Autowired
    private SpaceShipDtoMapper spaceShipDtoMapper;

    @Autowired
    private SpaceShipService spaceShipService;

    private SpaceShiptTestUtils spaceShiptTestUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        spaceShiptTestUtils = new SpaceShiptTestUtils();
    }

    @Test
    void getAllShipsWithOutAnyShipTest() {
        Page<SpaceShip> expectedResult = new PageImpl<>(List.of());

        when(spaceShipJpaPort.getAllShips(any(Pageable.class))).thenReturn(expectedResult);

        Page<SpaceShipDto> result = spaceShipService.getAllShips(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        verify(spaceShipJpaPort, times(1)).getAllShips(any(Pageable.class));
    }

    @Test
    void getAllShipsTest() {
        var listOfShips = spaceShiptTestUtils.generateSpaceShips(5);

        Page<SpaceShip> expectedResult = new PageImpl<>(listOfShips);

        when(spaceShipJpaPort.getAllShips(any(Pageable.class))).thenReturn(expectedResult);

        Page<SpaceShipDto> result = spaceShipService.getAllShips(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(5, result.getTotalElements());
        verify(spaceShipJpaPort, times(1)).getAllShips(any(Pageable.class));
    }

    @Test
    void getSpaceShipByIdtest() {
        var ship = spaceShiptTestUtils.generateSpaceShips(1).get(0);

        when(spaceShipJpaPort.getSpaceShipById(eq(1))).thenReturn(ship);

        var result = spaceShipService.getSpaceShipById(5);
        assertNull(result);
        result = spaceShipService.getSpaceShipById(1);
        assertNotNull(result);
        assertEquals(result.getName(),ship.getName());

    }

    @Test
    void findShipsByNameTest() {
        var ship = spaceShiptTestUtils.generateSpaceShips(2);

        when(spaceShipJpaPort.findShipsByName(eq("SpaceShip"))).thenReturn(ship);

        var result = spaceShipService.findShipsByName("aaa");
        assertTrue(result.isEmpty());
        result = spaceShipService.findShipsByName("SpaceShip");
        assertNotNull(result);
        assertEquals(result.size(),2);
        assertTrue(result.stream().allMatch(spaceShipDto -> spaceShipDto.getName().contains("SpaceShip")));
    }

}
