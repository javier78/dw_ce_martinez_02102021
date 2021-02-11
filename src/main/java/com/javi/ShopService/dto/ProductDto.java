package com.javi.ShopService.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
}
