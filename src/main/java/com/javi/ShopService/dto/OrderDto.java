package com.javi.ShopService.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Integer id;
    private String sku;
    private BigDecimal total;
    private LocalDateTime createdAt;
}
