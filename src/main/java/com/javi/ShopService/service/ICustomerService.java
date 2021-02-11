package com.javi.ShopService.service;

import com.javi.ShopService.dto.CreateCustomerRequestDto;
import com.javi.ShopService.entity.CustomerEntity;
import com.javi.ShopService.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICustomerService {
    CustomerEntity createCustomer(CreateCustomerRequestDto dto);
    List<OrderEntity> getCustomerOrders(Integer customerId);
    OrderEntity createOrderForCustomer(Integer customerId, String sku);
}
