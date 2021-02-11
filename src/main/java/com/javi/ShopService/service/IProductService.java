package com.javi.ShopService.service;

import com.javi.ShopService.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    List<ProductEntity> getAllProducts();
}
