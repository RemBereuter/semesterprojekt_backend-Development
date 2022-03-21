package com.example.semesterprojektbackend.controller;

import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Counts;
import com.example.semesterprojektbackend.service.CountsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CountsController.class})
@ExtendWith(SpringExtension.class)
class CountsControllerTest {
    @Autowired
    private CountsController countsController;

    @MockBean
    private CountsService countsService;

    @Test
    void testGetCounts() throws Exception {
        Counts counts = new Counts();
        counts.setProductsCount(3L);
        counts.setBrandsCount(3L);
        counts.setCategoriesCount(3L);
        counts.setUsersCount(3L);
        when(this.countsService.getCounts()).thenReturn(counts);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/counts");
        MockMvcBuilders.standaloneSetup(this.countsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"categoriesCount\":3,\"brandsCount\":3,\"productsCount\":3,\"usersCount\":3}"));
    }
}

