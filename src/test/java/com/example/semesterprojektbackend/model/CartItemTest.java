package com.example.semesterprojektbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CartItemTest {
    @Test
    void testConstructor() {
        CartItem actualCartItem = new CartItem();
        assertNull(actualCartItem.getCart());
        assertEquals(0, actualCartItem.getId());
        assertNull(actualCartItem.getProduct());
        assertEquals(1, actualCartItem.getQuantity());
    }
}

