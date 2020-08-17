package com.blablatest.lawnmower.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LawnConfigTest {

    @Test
    void should_not_validate_negative_X(){
        assertThrows(IllegalArgumentException.class, () -> new LawnConfig(-1, 0));
    }

    @Test
    void should_not_validate_negative_Y(){
        assertThrows(IllegalArgumentException.class, () -> new LawnConfig(0, -1));
    }

    @Test
    void should_get_MaxX(){
        assertEquals(10, new LawnConfig(10, 0).getMaxX());
    }

    @Test
    void should_get_MaxY(){
        assertEquals(0, new LawnConfig(10, 0).getMaxY());
    }

}