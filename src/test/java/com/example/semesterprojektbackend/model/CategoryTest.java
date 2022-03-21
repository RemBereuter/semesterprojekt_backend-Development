package com.example.semesterprojektbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CategoryTest {
    @Test
    void testConstructor() {
        Category actualCategory = new Category();
        actualCategory.setCategoryName("Category Name");
        actualCategory.setId(1);
        assertEquals("Category Name", actualCategory.getCategoryName());
        assertEquals(1, actualCategory.getId());
    }

    @Test
    void testConstructor2() {
        Category actualCategory = new Category(1, "Category Name");
        actualCategory.setCategoryName("Category Name");
        actualCategory.setId(1);
        assertEquals("Category Name", actualCategory.getCategoryName());
        assertEquals(1, actualCategory.getId());
    }
}

