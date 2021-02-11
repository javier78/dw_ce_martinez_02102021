package com.javi.ShopService.service;

import com.javi.ShopService.dto.CreateCustomerRequestDto;
import com.javi.ShopService.dto.CustomerDto;
import com.javi.ShopService.dto.OrderDto;
import com.javi.ShopService.entity.CustomerEntity;
import com.javi.ShopService.entity.OrderEntity;
import com.javi.ShopService.repository.CustomerRepository;
import com.javi.ShopService.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CustomerEntity createCustomer(CreateCustomerRequestDto dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        customerRepository.save(entity);

//        //Creating a separate DTO in case we add fields to the customer entity that we do not want returned in the createCustomer call
//        CustomerDto customerDto = new CustomerDto();
//        customerDto.setId(entity.getId());
//        customerDto.setName(entity.getName());
//        customerDto.setEmail(entity.getEmail());
        return entity;
    }

    @Override
    public List<OrderEntity> getCustomerOrders(Integer customerId) {
        List<OrderEntity> orders = orderRepository.findByCustomerId(customerId);
        return null;
    }

    @Override
    public OrderEntity createOrderForCustomer(Integer customerId, String sku) {
        return null;
    }
}
