package com.javi.ShopService.controller;

import com.javi.ShopService.dto.CreateCustomerRequestDto;
import com.javi.ShopService.dto.CreateOrderRequestDto;
import com.javi.ShopService.dto.CustomerDto;
import com.javi.ShopService.dto.OrderDto;
import com.javi.ShopService.entity.CustomerEntity;
import com.javi.ShopService.entity.OrderEntity;
import com.javi.ShopService.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Mock
    private CustomerService mockCustomerService;
    @Mock
    private ModelMapper mockModelMapper;
    @InjectMocks
    private CustomerController customerController;

    @Test
    public void happyPathCreateCustomerEndpoint() throws Exception {
        //Init
        CreateCustomerRequestDto dto = new CreateCustomerRequestDto();
        dto.setName("Test 123");
        dto.setEmail("Test@test.com");

        CustomerEntity entity = new CustomerEntity();
        entity.setId(1);
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1);
        customerDto.setName(dto.getName());
        customerDto.setEmail(dto.getEmail());
        //Mock
        Mockito.when(mockCustomerService.createCustomer(Mockito.any(CreateCustomerRequestDto.class))).thenReturn(entity);
        Mockito.when(mockModelMapper.map(entity, CustomerDto.class)).thenReturn(customerDto);
        //Test
        ResponseEntity<?> response = customerController.createCustomer(dto);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void sadPathCreateCustomerEndpoint() throws Exception {
        //Init
        CreateCustomerRequestDto dto = new CreateCustomerRequestDto();
        dto.setName("Test 123");
        dto.setEmail("Test@test.com");
        //Mock
        Mockito.when(mockCustomerService.createCustomer(Mockito.any(CreateCustomerRequestDto.class))).thenThrow(new Exception());
        //Test
        ResponseEntity<?> response = customerController.createCustomer(dto);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(500, response.getStatusCode().value());
    }

    @Test
    public void happyPathGetOrdersForCustomer() throws Exception {
        //Init
        Integer customerId = 1;

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setCustomerId(1);

        OrderDto orderDto = new OrderDto();
        orderDto.setId(1);

        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(orderEntity);

        //Mock
        Mockito.when(mockCustomerService.getCustomerOrders(customerId)).thenReturn(orderEntities);
        Mockito.when(mockModelMapper.map(orderEntity, OrderDto.class)).thenReturn(orderDto);

        //Test
        ResponseEntity<?> response = customerController.getOrdersForCustomer(customerId);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void sadPathGetOrdersForCustomer() throws Exception {
        //Init
        Integer customerId = 1;

        //Mock
        Mockito.when(mockCustomerService.getCustomerOrders(customerId)).thenThrow(new Exception());

        //Test
        ResponseEntity<?> response = customerController.getOrdersForCustomer(customerId);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(500, response.getStatusCode().value());
    }

    @Test
    public void happyPathCreateOrderForCustomer() throws Exception {
        //Init
        Integer customerId = 1;
        CreateOrderRequestDto orderRequestDto = new CreateOrderRequestDto();
        orderRequestDto.setSku("123456");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setCustomerId(1);

        OrderDto orderDto = new OrderDto();
        orderDto.setId(1);

        //Mock
        Mockito.when(mockCustomerService.createOrderForCustomer(customerId, orderRequestDto)).thenReturn(orderEntity);
        Mockito.when(mockModelMapper.map(orderEntity, OrderDto.class)).thenReturn(orderDto);

        //Test
        ResponseEntity<?> response = customerController.createOrderForGivenCustomer(customerId, orderRequestDto);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void sadPathCreateOrderForCustomer() throws Exception {
        //Init
        Integer customerId = 1;
        CreateOrderRequestDto orderRequestDto = new CreateOrderRequestDto();
        orderRequestDto.setSku("123456");

        //Mock
        Mockito.when(mockCustomerService.createOrderForCustomer(customerId, orderRequestDto)).thenThrow(new Exception());

        //Test
        ResponseEntity<?> response = customerController.createOrderForGivenCustomer(customerId, orderRequestDto);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(500, response.getStatusCode().value());
    }
}
