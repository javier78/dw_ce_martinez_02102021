package com.javi.ShopService.dto;

import lombok.Data;

@Data
public class CreateCustomerRequestDto {
    private String email;
    private String name;
}
