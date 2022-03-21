package com.example.semesterprojektbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.semesterprojektbackend.model.enumuration.Size;
import com.example.semesterprojektbackend.model.enumuration.Status;
import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    void testConstructor() {
        Product actualProduct = new Product();
        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");
        actualProduct.setBrand(brand);
        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);
        actualProduct.setCategory(category);
        actualProduct.setDescription("The characteristics of someone or something");
        actualProduct.setImageUrl("https://example.org/example");
        actualProduct.setItemNumber(42L);
        actualProduct.setName("Name");
        actualProduct.setPrice(10.0);
        actualProduct.setSize(Size.S);
        actualProduct.setStatus(Status.ACTIVE);
        assertSame(brand, actualProduct.getBrand());
        assertSame(category, actualProduct.getCategory());
        assertEquals("The characteristics of someone or something", actualProduct.getDescription());
        assertEquals("https://example.org/example", actualProduct.getImageUrl());
        assertEquals(42L, actualProduct.getItemNumber().longValue());
        assertEquals("Name", actualProduct.getName());
        assertEquals(10.0, actualProduct.getPrice());
        assertEquals(Size.S, actualProduct.getSize());
        assertEquals(Status.ACTIVE, actualProduct.getStatus());
    }

    @Test
    void testConstructor2() {
        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");
        Product actualProduct = new Product(42L, "Name", "The characteristics of someone or something", Size.S, category,
                Status.ACTIVE, 10.0, brand, "https://example.org/example");

        assertSame(brand, actualProduct.getBrand());
        assertEquals(Status.ACTIVE, actualProduct.getStatus());
        assertSame(category, actualProduct.getCategory());
        assertEquals("https://example.org/example", actualProduct.getImageUrl());
        assertEquals(Size.S, actualProduct.getSize());
        assertEquals(42L, actualProduct.getItemNumber().longValue());
        assertEquals("The characteristics of someone or something", actualProduct.getDescription());
        assertEquals(10.0, actualProduct.getPrice());
        assertEquals("Name", actualProduct.getName());
    }
}

