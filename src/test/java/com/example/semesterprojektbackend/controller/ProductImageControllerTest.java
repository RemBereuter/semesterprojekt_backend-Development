package com.example.semesterprojektbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Brand;
import com.example.semesterprojektbackend.model.Category;
import com.example.semesterprojektbackend.model.Product;
import com.example.semesterprojektbackend.model.ProductImageDTO;
import com.example.semesterprojektbackend.model.enumuration.Size;
import com.example.semesterprojektbackend.model.enumuration.Status;
import com.example.semesterprojektbackend.repositories.ProductRepo;
import com.example.semesterprojektbackend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductImageController.class})
@ExtendWith(SpringExtension.class)
class ProductImageControllerTest {
    @Autowired
    private ProductImageController productImageController;

    @MockBean
    private ProductRepo productRepo;

    @MockBean
    private ProductService productService;

    @Test
    void testAddNewProductImageUrl() {
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
        when(this.productService.findByItemNumber((Long) any())).thenReturn(product);

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
        when(this.productRepo.save((Product) any())).thenReturn(product1);

        ProductImageDTO productImageDTO = new ProductImageDTO();
        productImageDTO.setProductImageUrl("https://example.org/example");
        productImageDTO.setItemNumber(42L);
        assertEquals("Product image URL Created", this.productImageController.addNewProductImageUrl(productImageDTO));
        verify(this.productService).findByItemNumber((Long) any());
        verify(this.productRepo).save((Product) any());
    }
}

