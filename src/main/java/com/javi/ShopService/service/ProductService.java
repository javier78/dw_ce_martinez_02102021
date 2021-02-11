package com.javi.ShopService.service;

import com.javi.ShopService.entity.ProductEntity;
import com.javi.ShopService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> entities = productRepository.findAll();
        return entities;
    }
}
