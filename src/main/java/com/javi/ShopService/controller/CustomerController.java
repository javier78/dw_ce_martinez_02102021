package com.javi.ShopService.controller;

import com.javi.ShopService.dto.CreateCustomerRequestDto;
import com.javi.ShopService.dto.CreateOrderRequestDto;
import com.javi.ShopService.dto.CustomerDto;
import com.javi.ShopService.dto.OrderDto;
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
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequestDto body) {
        CustomerDto dto = convertCustomerEntityToDto(customerService.createCustomer(body));
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "{id}/orders")
    public ResponseEntity<List<OrderDto>> getOrdersForCustomer(@PathVariable(name = "id") Integer customerId) {
        List<OrderEntity> orderEntities = customerService.getCustomerOrders(customerId);

        return ResponseEntity.ok(orderEntities.stream().map(this::convertOrderEntityToDto).collect(Collectors.toList()));
    }

    @PostMapping(path = "{id}/orders")
    public ResponseEntity<OrderDto> createOrderForGivenCustomer(@PathVariable(name = "id") Integer customerId, @RequestBody CreateOrderRequestDto sku) {
        OrderEntity entity = customerService.createOrderForCustomer(customerId, sku);

        return ResponseEntity.ok(convertOrderEntityToDto(entity));
    }

    @PostMapping(path = "{id}/notifications")
    public ResponseEntity<?> createNotificationForProductAndCustomer(@PathVariable(name = "id") Integer customerId) {
        return null;
    }

    private CustomerDto convertCustomerEntityToDto(CustomerEntity entity) {
        CustomerDto dto = modelMapper.map(entity, CustomerDto.class);
        return dto;
    }

    private OrderDto convertOrderEntityToDto(OrderEntity entity) {
        return modelMapper.map(entity, OrderDto.class);
    }
}
