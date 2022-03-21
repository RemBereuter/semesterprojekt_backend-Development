package com.example.semesterprojektbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Brand;
import com.example.semesterprojektbackend.model.Category;
import com.example.semesterprojektbackend.model.Product;
import com.example.semesterprojektbackend.model.enumuration.Size;
import com.example.semesterprojektbackend.model.enumuration.Status;
import com.example.semesterprojektbackend.repositories.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @MockBean
    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;

    @Test
    void testFindAll() {
        ArrayList<Product> productList = new ArrayList<>();
        when(this.productRepo.findAll()).thenReturn(productList);
        List<Product> actualFindAllResult = this.productService.findAll();
        assertSame(productList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.productRepo).findAll();
        assertEquals(actualFindAllResult, this.productService.getActiveProducts());
    }

    @Test
    void testGetActiveProducts() {
        when(this.productRepo.findAll()).thenReturn(new ArrayList<>());
        List<Product> actualActiveProducts = this.productService.getActiveProducts();
        assertTrue(actualActiveProducts.isEmpty());
        verify(this.productRepo).findAll();
        assertEquals(actualActiveProducts, this.productService.findAll());
    }

    @Test
    void testGetActiveProducts2() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(this.productRepo.findAll()).thenReturn(productList);
        List<Product> actualActiveProducts = this.productService.getActiveProducts();
        assertEquals(1, actualActiveProducts.size());
        verify(this.productRepo).findAll();
        assertEquals(actualActiveProducts, this.productService.findAll());
    }

    @Test
    void testGetActiveProducts3() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);

        Brand brand1 = new Brand();
        brand1.setId(1);
        brand1.setBrandName("Brand Name");

        Category category1 = new Category();
        category1.setCategoryName("Category Name");
        category1.setId(1);

        Product product1 = new Product();
        product1.setStatus(Status.ACTIVE);
        product1.setBrand(brand1);
        product1.setPrice(10.0);
        product1.setImageUrl("https://example.org/example");
        product1.setName("Name");
        product1.setSize(Size.S);
        product1.setCategory(category1);
        product1.setDescription("The characteristics of someone or something");
        product1.setItemNumber(42L);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product);
        when(this.productRepo.findAll()).thenReturn(productList);
        List<Product> actualActiveProducts = this.productService.getActiveProducts();
        assertEquals(2, actualActiveProducts.size());
        verify(this.productRepo).findAll();
        assertEquals(actualActiveProducts, this.productService.findAll());
    }

    @Test
    void testGetActiveProducts4() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.NOT_ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);

        Brand brand1 = new Brand();
        brand1.setId(1);
        brand1.setBrandName("Brand Name");

        Category category1 = new Category();
        category1.setCategoryName("Category Name");
        category1.setId(1);

        Product product1 = new Product();
        product1.setStatus(Status.ACTIVE);
        product1.setBrand(brand1);
        product1.setPrice(10.0);
        product1.setImageUrl("https://example.org/example");
        product1.setName("Name");
        product1.setSize(Size.S);
        product1.setCategory(category1);
        product1.setDescription("The characteristics of someone or something");
        product1.setItemNumber(42L);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product);
        when(this.productRepo.findAll()).thenReturn(productList);
        assertEquals(1, this.productService.getActiveProducts().size());
        verify(this.productRepo).findAll();
        assertEquals(2, this.productService.findAll().size());
    }

    @Test
    void testSave() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);
        when(this.productRepo.save((Product) any())).thenReturn(product);

        Brand brand1 = new Brand();
        brand1.setId(1);
        brand1.setBrandName("Brand Name");

        Category category1 = new Category();
        category1.setCategoryName("Category Name");
        category1.setId(1);

        Product product1 = new Product();
        product1.setStatus(Status.ACTIVE);
        product1.setBrand(brand1);
        product1.setPrice(10.0);
        product1.setImageUrl("https://example.org/example");
        product1.setName("Name");
        product1.setSize(Size.S);
        product1.setCategory(category1);
        product1.setDescription("The characteristics of someone or something");
        product1.setItemNumber(42L);
        assertSame(product1, this.productService.save(product1));
        verify(this.productRepo).save((Product) any());
        assertTrue(this.productService.findAll().isEmpty());
    }

    @Test
    void testFindByItemNumber() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);
        when(this.productRepo.findByItemNumber((Long) any())).thenReturn(product);
        assertSame(product, this.productService.findByItemNumber(42L));
        verify(this.productRepo).findByItemNumber((Long) any());
        assertTrue(this.productService.findAll().isEmpty());
    }

    @Test
    void testFindById() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);
        Optional<Product> ofResult = Optional.of(product);
        when(this.productRepo.findById((Long) any())).thenReturn(ofResult);
        Optional<Product> actualFindByIdResult = this.productService.findById(42L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.productRepo).findById((Long) any());
        assertTrue(this.productService.findAll().isEmpty());
    }

    @Test
    void testDelete() {
        doNothing().when(this.productRepo).deleteById((Long) any());
        this.productService.delete(123L);
        verify(this.productRepo).deleteById((Long) any());
        assertTrue(this.productService.findAll().isEmpty());
    }
}

