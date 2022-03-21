package com.example.semesterprojektbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CartDTOTest {
    @Test
    void testConstructor() {
        CartDTO actualCartDTO = new CartDTO();
        actualCartDTO.setCartItemsCount(3);
        actualCartDTO.setCartTotal(10.0f);
        assertEquals(3, actualCartDTO.getCartItemsCount());
        assertEquals(10.0f, actualCartDTO.getCartTotal());
    }

    @Test
    void testConstructor2() {
        CartDTO actualCartDTO = new CartDTO(3, 10.0f);
        actualCartDTO.setCartItemsCount(3);
        actualCartDTO.setCartTotal(10.0f);
        assertEquals(3, actualCartDTO.getCartItemsCount());
        assertEquals(10.0f, actualCartDTO.getCartTotal());
    }
}

