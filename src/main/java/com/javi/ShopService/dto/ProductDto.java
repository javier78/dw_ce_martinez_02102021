package com.javi.ShopService.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private String sku;
}
