package com.javi.ShopService.controller;

import com.javi.ShopService.dto.*;
import com.javi.ShopService.entity.CustomerEntity;
import com.javi.ShopService.entity.OrderEntity;
import com.javi.ShopService.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerRequestDto body) {
        try {
            CustomerDto dto = convertCustomerEntityToDto(customerService.createCustomer(body));
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorDto(e.getMessage()));
        }
    }

    @GetMapping(path = "{id}/orders")
    public ResponseEntity<?> getOrdersForCustomer(@PathVariable(name = "id") Integer customerId) {
        try {
            List<OrderEntity> orderEntities = customerService.getCustomerOrders(customerId);
            return ResponseEntity.ok(orderEntities.stream().map(this::convertOrderEntityToDto).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorDto(e.getMessage()));
        }
    }

    @PostMapping(path = "{id}/orders")
    public ResponseEntity<?> createOrderForGivenCustomer(@PathVariable(name = "id") Integer customerId, @RequestBody CreateOrderRequestDto sku) {
        try {
            OrderEntity entity = customerService.createOrderForCustomer(customerId, sku);
            return ResponseEntity.ok(convertOrderEntityToDto(entity));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorDto(e.getMessage()));
        }
    }

    private CustomerDto convertCustomerEntityToDto(CustomerEntity entity) {
        CustomerDto dto = modelMapper.map(entity, CustomerDto.class);
        return dto;
    }

    private OrderDto convertOrderEntityToDto(OrderEntity entity) {
        return modelMapper.map(entity, OrderDto.class);
    }
}
