package com.example.semesterprojektbackend.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Category;
import com.example.semesterprojektbackend.repositories.CategoryRepo;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryService.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryService categoryService;

    @Test
    void testGetCategories() {
        ArrayList<Category> categoryList = new ArrayList<>();
        when(this.categoryRepo.findAll()).thenReturn(categoryList);
        List<Category> actualCategories = this.categoryService.getCategories();
        assertSame(categoryList, actualCategories);
        assertTrue(actualCategories.isEmpty());
        verify(this.categoryRepo).findAll();
    }

    @Test
    void testSave() {
        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);
        when(this.categoryRepo.save((Category) any())).thenReturn(category);

        Category category1 = new Category();
        category1.setCategoryName("Category Name");
        category1.setId(1);
        assertSame(category1, this.categoryService.save(category1));
        verify(this.categoryRepo).save((Category) any());
        assertTrue(this.categoryService.getCategories().isEmpty());
    }

    @Test
    void testFindById() {
        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);
        Optional<Category> ofResult = Optional.of(category);
        when(this.categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        Optional<Category> actualFindByIdResult = this.categoryService.findById(1);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.categoryRepo).findById((Integer) any());
        assertTrue(this.categoryService.getCategories().isEmpty());
    }

    @Test
    void testDelete() {
        doNothing().when(this.categoryRepo).deleteById((Integer) any());
        this.categoryService.delete(1);
        verify(this.categoryRepo).deleteById((Integer) any());
        assertTrue(this.categoryService.getCategories().isEmpty());
    }
}

