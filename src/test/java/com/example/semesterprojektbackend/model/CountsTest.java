package com.example.semesterprojektbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CountsTest {
    @Test
    void testConstructor() {
        Counts actualCounts = new Counts();
        actualCounts.setBrandsCount(3L);
        actualCounts.setCategoriesCount(3L);
        actualCounts.setProductsCount(3L);
        actualCounts.setUsersCount(3L);
        assertEquals(3L, actualCounts.getBrandsCount().longValue());
        assertEquals(3L, actualCounts.getCategoriesCount().longValue());
        assertEquals(3L, actualCounts.getProductsCount().longValue());
        assertEquals(3L, actualCounts.getUsersCount().longValue());
    }
}

