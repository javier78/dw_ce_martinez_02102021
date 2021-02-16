package com.javi.ShopService.controller;

import com.javi.ShopService.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    private ProductService mockProductService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void happyPathGetProducts() {
        //Mock
        Mockito.when(mockProductService.getAllProducts()).thenReturn(new ArrayList<>());

        //Test
        ResponseEntity<?> response = productController.getProducts();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }
}
