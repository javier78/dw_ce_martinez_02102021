package com.javi.ShopService.controller;

import com.javi.ShopService.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    private ProductService mockProductService;
    @Mock
    private ModelMapper mockModelMapper;

    @Test
    public void happyPathGetProducts() {
        
    }
}
