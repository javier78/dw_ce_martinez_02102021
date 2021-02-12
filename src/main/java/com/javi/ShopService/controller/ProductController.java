package com.javi.ShopService.controller;

import com.javi.ShopService.dto.ProductDto;
import com.javi.ShopService.entity.ProductEntity;
import com.javi.ShopService.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductEntity> productEntities = productService.getAllProducts();
        return ResponseEntity.ok(productEntities.stream().map(this::convertProductEntityToDto).collect(Collectors.toList()));
    }

    private ProductDto convertProductEntityToDto(ProductEntity entity) {
        return modelMapper.map(entity, ProductDto.class);
    }
}
