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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.RedisConnectionFailureException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SpaceShipServiceTest {

    @Mock
    private SpaceShipJpaPort spaceShipJpaPort;

    @Mock
    private SpaceShipDtoMapper spaceShipDtoMapper;

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private SpaceShipService spaceShipService;

    private SpaceShiptTestUtils spaceShiptTestUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        spaceShiptTestUtils = new SpaceShiptTestUtils();
    }

    @Test
    void GetAllShipsWithOutAnyShipTest() {
        Page<SpaceShip> expectedResult = new PageImpl<>(List.of());

        when(spaceShipJpaPort.getAllShips(any(Pageable.class))).thenReturn(expectedResult);

        Page<SpaceShipDto> result = spaceShipService.getAllShips(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        verify(spaceShipJpaPort, times(1)).getAllShips(any(Pageable.class));
    }

    @Test
    void GetAllShipsTest() {
        var listOfShips = spaceShiptTestUtils.generateSpaceShips(5);

        Page<SpaceShip> expectedResult = new PageImpl<>(listOfShips);

        when(spaceShipJpaPort.getAllShips(any(Pageable.class))).thenReturn(expectedResult);

        Page<SpaceShipDto> result = spaceShipService.getAllShips(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(5, result.getTotalElements());
        verify(spaceShipJpaPort, times(1)).getAllShips(any(Pageable.class));
    }


    // 2. Test getSpaceShipById
    @Test
    void testGetSpaceShipById() {

        //when(spaceShipJpaPort.getSpaceShipById(anyInt())).thenReturn(Optional.of(new SpaceShipDto()));

        SpaceShipDto result = spaceShipService.getSpaceShipById(5);

        assertNotNull(result);
        verify(spaceShipJpaPort, times(1)).getSpaceShipById(5);
    }
/*
    // 3. Test findShipsByName
    @Test
    void testFindShipsByName_ReturnsList() {
        List<SpaceShipDto> ships = List.of(new SpaceShipDto());
        when(spaceShipJpaPort.findShipsByName(anyString())).thenReturn(ships);

        List<SpaceShipDto> result = spaceShipService.findShipsByName("Falcon");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(spaceShipJpaPort, times(1)).findShipsByName("Falcon");
    }

    // 4. Test createSpaceShip
    @Test
    void testCreateSpaceShip_CallsClearCaches() {
        SpaceShipRequest request = new SpaceShipRequest();
        SpaceShipDto spaceShipDto = new SpaceShipDto();

        when(spaceShipDtoMapper.toDomain(any(SpaceShipRequest.class))).thenReturn(spaceShipDto);
        when(spaceShipJpaPort.createSpaceShip(any())).thenReturn(spaceShipDto);

        spaceShipService.createSpaceShip(request);

        verify(spaceShipJpaPort, times(1)).createSpaceShip(spaceShipDto);
        verify(cacheService, times(1)).clearCaches();
    }

    // 5. Test updateSpaceShip
    @Test
    void testUpdateSpaceShip_CallsClearCaches() {
        SpaceShipRequest request = new SpaceShipRequest();
        SpaceShipDto spaceShipDto = new SpaceShipDto();

        when(spaceShipDtoMapper.toDomain(any(SpaceShipRequest.class))).thenReturn(spaceShipDto);
        when(spaceShipJpaPort.updateSpaceShip(any())).thenReturn(spaceShipDto);

        spaceShipService.updateSpaceShip(5, request);

        verify(spaceShipJpaPort, times(1)).updateSpaceShip(spaceShipDto);
        verify(cacheService, times(1)).clearCaches();
    }

    // 6. Test deleteSpaceShip
    @Test
    void testDeleteSpaceShip_CallsClearCaches() {
        when(spaceShipJpaPort.deleteSpaceShip(anyInt())).thenReturn(true);

        Boolean result = spaceShipService.deleteSpaceShip(5);

        assertTrue(result);
        verify(spaceShipJpaPort, times(1)).deleteSpaceShip(5);
        verify(cacheService, times(1)).clearCaches();
    }

    // 7. Test Cache Service with Exception
    @Test
    void testCacheClear_ThrowsRedisConnectionFailureException() {
        doThrow(new RedisConnectionFailureException("Redis error")).when(cacheService).clearCaches();

        assertDoesNotThrow(() -> spaceShipService.createSpaceShip(new SpaceShipRequest()));
    }*/
}
