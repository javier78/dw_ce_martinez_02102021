package com.javi.ShopService.service;

import com.javi.ShopService.dto.CreateCustomerRequestDto;
import com.javi.ShopService.dto.CreateOrderRequestDto;
import com.javi.ShopService.entity.CustomerEntity;
import com.javi.ShopService.entity.OrderEntity;
import com.javi.ShopService.entity.ProductEntity;
import com.javi.ShopService.repository.CustomerRepository;
import com.javi.ShopService.repository.OrderRepository;
import com.javi.ShopService.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CustomerEntity createCustomer(CreateCustomerRequestDto dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        customerRepository.save(entity);
        return entity;
    }

    @Override
    public List<OrderEntity> getCustomerOrders(Integer customerId) {
        List<OrderEntity> orders = orderRepository.findByCustomerId(customerId);
        return orders;
    }

    @Override
    public OrderEntity createOrderForCustomer(Integer customerId, CreateOrderRequestDto skuDto) {
        OrderEntity orderEntity = new OrderEntity();
        ProductEntity productEntity = productRepository.findBySku(skuDto.getSku());
        orderEntity.setCustomerId(customerId);
        orderEntity.setSku(skuDto.getSku());
        orderEntity.setCreatedAt(LocalDateTime.now());
        orderEntity.setTotal(productEntity.getPrice());
        orderRepository.save(orderEntity);
        return orderEntity;
    }
}
