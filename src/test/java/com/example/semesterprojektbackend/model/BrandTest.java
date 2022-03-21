package com.example.semesterprojektbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BrandTest {
    @Test
    void testConstructor() {
        Brand actualBrand = new Brand();
        actualBrand.setBrandName("Brand Name");
        actualBrand.setId(1);
        assertEquals("Brand Name", actualBrand.getBrandName());
        assertEquals(1, actualBrand.getId());
    }

    @Test
    void testConstructor2() {
        Brand actualBrand = new Brand(1, "Brand Name");
        actualBrand.setBrandName("Brand Name");
        actualBrand.setId(1);
        assertEquals("Brand Name", actualBrand.getBrandName());
        assertEquals(1, actualBrand.getId());
    }
}

