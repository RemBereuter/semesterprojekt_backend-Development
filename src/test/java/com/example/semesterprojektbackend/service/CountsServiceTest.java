package com.example.semesterprojektbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Counts;
import com.example.semesterprojektbackend.repositories.BrandRepo;
import com.example.semesterprojektbackend.repositories.CategoryRepo;
import com.example.semesterprojektbackend.repositories.ProductRepo;
import com.example.semesterprojektbackend.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CountsService.class})
@ExtendWith(SpringExtension.class)
class CountsServiceTest {
    @MockBean
    private BrandRepo brandRepo;

    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private CountsService countsService;

    @MockBean
    private ProductRepo productRepo;

    @MockBean
    private UserRepo userRepo;

    @Test
    void testGetCounts() {
        when(this.userRepo.count()).thenReturn(3L);
        when(this.productRepo.count()).thenReturn(3L);
        when(this.categoryRepo.count()).thenReturn(3L);
        when(this.brandRepo.count()).thenReturn(3L);
        Counts actualCounts = this.countsService.getCounts();
        assertEquals(3L, actualCounts.getBrandsCount().longValue());
        assertEquals(3L, actualCounts.getUsersCount().longValue());
        assertEquals(3L, actualCounts.getProductsCount().longValue());
        assertEquals(3L, actualCounts.getCategoriesCount().longValue());
        verify(this.userRepo).count();
        verify(this.productRepo).count();
        verify(this.categoryRepo).count();
        verify(this.brandRepo).count();
    }
}

