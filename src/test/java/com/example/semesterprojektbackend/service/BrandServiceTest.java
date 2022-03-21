package com.example.semesterprojektbackend.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Brand;
import com.example.semesterprojektbackend.repositories.BrandRepo;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BrandService.class})
@ExtendWith(SpringExtension.class)
class BrandServiceTest {
    @MockBean
    private BrandRepo brandRepo;

    @Autowired
    private BrandService brandService;

    @Test
    void testGetBrands() {
        ArrayList<Brand> brandList = new ArrayList<>();
        when(this.brandRepo.findAll()).thenReturn(brandList);
        List<Brand> actualBrands = this.brandService.getBrands();
        assertSame(brandList, actualBrands);
        assertTrue(actualBrands.isEmpty());
        verify(this.brandRepo).findAll();
    }

    @Test
    void testSave() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");
        when(this.brandRepo.save((Brand) any())).thenReturn(brand);

        Brand brand1 = new Brand();
        brand1.setId(1);
        brand1.setBrandName("Brand Name");
        assertSame(brand1, this.brandService.save(brand1));
        verify(this.brandRepo).save((Brand) any());
        assertTrue(this.brandService.getBrands().isEmpty());
    }

    @Test
    void testFindById() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");
        Optional<Brand> ofResult = Optional.of(brand);
        when(this.brandRepo.findById((Integer) any())).thenReturn(ofResult);
        Optional<Brand> actualFindByIdResult = this.brandService.findById(1);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.brandRepo).findById((Integer) any());
        assertTrue(this.brandService.getBrands().isEmpty());
    }

    @Test
    void testDelete() {
        doNothing().when(this.brandRepo).deleteById((Integer) any());
        this.brandService.delete(1);
        verify(this.brandRepo).deleteById((Integer) any());
        assertTrue(this.brandService.getBrands().isEmpty());
    }
}

