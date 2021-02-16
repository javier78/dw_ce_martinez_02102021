package com.javi.ShopService.service;

import com.javi.ShopService.entity.ProductEntity;
import com.javi.ShopService.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository mockProductRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void happyPathGetAllProductsNoProducts() {
        //Mock
        Mockito.when(mockProductRepository.findAll()).thenReturn(new ArrayList<>());
        //Test
        List<ProductEntity> products = productService.getAllProducts();
        Assertions.assertEquals(0, products.size());
    }

    @Test
    public void happyPathGetAllProductsOneProduct() {
        //Init
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setName("Nintendo Switch");
        productEntity.setSku("123456");
        productEntity.setPrice(BigDecimal.valueOf(300.00));
        productEntity.setDescription("It's a Nintendo Switch!");

        List<ProductEntity> entities = new ArrayList<>();
        entities.add(productEntity);
        //Mock
        Mockito.when(mockProductRepository.findAll()).thenReturn(entities);
        //Test
        List<ProductEntity> results = productService.getAllProducts();
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Nintendo Switch", results.get(0).getName());
    }

    @Test
    public void happyPathGetAllProductsMultipleProducts() {
        //Init
        ProductEntity productEntityOne = new ProductEntity();
        productEntityOne.setId(1);
        productEntityOne.setName("Nintendo Switch");
        productEntityOne.setSku("123456");
        productEntityOne.setPrice(BigDecimal.valueOf(300.00));
        productEntityOne.setDescription("It's a Nintendo Switch!");

        ProductEntity productEntityTwo = new ProductEntity();
        productEntityTwo.setId(2);
        productEntityTwo.setName("Playstation 5");
        productEntityTwo.setSku("78910");
        productEntityTwo.setPrice(BigDecimal.valueOf(499.99));
        productEntityTwo.setDescription("It's a Playstation 5!");

        List<ProductEntity> entities = new ArrayList<>();
        entities.add(productEntityOne);
        entities.add(productEntityTwo);
        //Mock
        Mockito.when(mockProductRepository.findAll()).thenReturn(entities);
        //Test
        List<ProductEntity> results = productService.getAllProducts();
        Assertions.assertEquals(2, results.size());
        Assertions.assertEquals("Nintendo Switch", results.get(0).getName());
        Assertions.assertEquals("Playstation 5", results.get(1).getName());
    }
}
