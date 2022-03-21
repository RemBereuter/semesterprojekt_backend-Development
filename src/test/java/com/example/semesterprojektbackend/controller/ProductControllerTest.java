package com.example.semesterprojektbackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Brand;
import com.example.semesterprojektbackend.model.Category;
import com.example.semesterprojektbackend.model.Product;
import com.example.semesterprojektbackend.model.enumuration.Size;
import com.example.semesterprojektbackend.model.enumuration.Status;
import com.example.semesterprojektbackend.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void testAddNew() throws Exception {
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
        when(this.productService.save((Product) any())).thenReturn(product);

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
        String content = (new ObjectMapper()).writeValueAsString(product1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product created"));
    }

    @Test
    void testGetActiveProducts() throws Exception {
        when(this.productService.getActiveProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/activeproducts");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetActiveProducts2() throws Exception {
        when(this.productService.getActiveProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/activeproducts");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(this.productService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/{itemNumber}", 42L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"deleted\":true}"));
    }

    @Test
    void testDeleteProduct2() throws Exception {
        doNothing().when(this.productService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/products/{itemNumber}", 42L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"deleted\":true}"));
    }

    @Test
    void testGetProductById() throws Exception {
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
        when(this.productService.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{itemNumber}", 42L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"itemNumber\":42,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"size\":\"S"
                                        + "\",\"category\":{\"id\":1,\"categoryName\":\"Category Name\"},\"status\":\"ACTIVE\",\"price\":10.0,\"brand\":{\"id\":1,"
                                        + "\"brandName\":\"Brand Name\"},\"imageUrl\":\"https://example.org/example\"}"));
    }

    @Test
    void testGetProductById2() throws Exception {
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
        Optional<Product> ofResult = Optional.of(product);
        when(this.productService.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{itemNumber}", 42L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("null"));
    }

    @Test
    void testGetProductById3() throws Exception {
        when(this.productService.findById((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{itemNumber}", 42L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("null"));
    }

    @Test
    void testGetProductPath() throws Exception {
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
        when(this.productService.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product-by-item-number/{itemNumber}",
                42L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"itemNumber\":42,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"size\":\"S"
                                        + "\",\"category\":{\"id\":1,\"categoryName\":\"Category Name\"},\"status\":\"ACTIVE\",\"price\":10.0,\"brand\":{\"id\":1,"
                                        + "\"brandName\":\"Brand Name\"},\"imageUrl\":\"https://example.org/example\"}"));
    }

    @Test
    void testGetProductPath2() throws Exception {
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
        Optional<Product> ofResult = Optional.of(product);
        when(this.productService.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product-by-item-number/{itemNumber}",
                42L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("null"));
    }

    @Test
    void testGetProductPath3() throws Exception {
        when(this.productService.findById((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product-by-item-number/{itemNumber}",
                42L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("null"));
    }

    @Test
    void testGetProducts() throws Exception {
        when(this.productService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetProducts2() throws Exception {
        when(this.productService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/products");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdateProduct() throws Exception {
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
        when(this.productService.save((Product) any())).thenReturn(product1);
        when(this.productService.findById((Long) any())).thenReturn(ofResult);

        Brand brand2 = new Brand();
        brand2.setId(1);
        brand2.setBrandName("Brand Name");

        Category category2 = new Category();
        category2.setCategoryName("Category Name");
        category2.setId(1);

        Product product2 = new Product();
        product2.setStatus(Status.ACTIVE);
        product2.setBrand(brand2);
        product2.setPrice(10.0);
        product2.setImageUrl("https://example.org/example");
        product2.setName("Name");
        product2.setSize(Size.S);
        product2.setCategory(category2);
        product2.setDescription("The characteristics of someone or something");
        product2.setItemNumber(42L);
        String content = (new ObjectMapper()).writeValueAsString(product2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/{itemNumber}", 42L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"itemNumber\":42,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"size\":\"S"
                                        + "\",\"category\":{\"id\":1,\"categoryName\":\"Category Name\"},\"status\":\"ACTIVE\",\"price\":10.0,\"brand\":{\"id\":1,"
                                        + "\"brandName\":\"Brand Name\"},\"imageUrl\":\"https://example.org/example\"}"));
    }
}

