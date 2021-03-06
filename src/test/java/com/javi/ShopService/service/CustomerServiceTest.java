package com.javi.ShopService.service;

import com.javi.ShopService.dto.CreateCustomerRequestDto;
import com.javi.ShopService.dto.CreateOrderRequestDto;
import com.javi.ShopService.entity.CustomerEntity;
import com.javi.ShopService.entity.OrderEntity;
import com.javi.ShopService.entity.ProductEntity;
import com.javi.ShopService.repository.CustomerRepository;
import com.javi.ShopService.repository.OrderRepository;
import com.javi.ShopService.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository mockCustomerRepository;

    @Mock
    private OrderRepository mockOrderRepository;

    @Mock
    private ProductRepository mockProductRepository;

    @InjectMocks
    private CustomerService customerService;

    /**
     * Happy path test for customer creation.
     * Verifies that the name and email from the request DTO are saved to an entity.
     */
    @Test
    public void createCustomerTest() {
        CreateCustomerRequestDto customerDto = new CreateCustomerRequestDto();
        CustomerEntity entity;
        customerDto.setEmail("test123@test.com");
        customerDto.setName("TestName");

        try {
            entity = customerService.createCustomer(customerDto);
            assertEquals("test123@test.com", entity.getEmail());
            assertEquals("TestName", entity.getName());
        } catch (Exception e) {
            throw new AssertionError("Exception thrown: " + e.getMessage());
        }

    }

    /**
     * Sad path test for customer creation
     * Verifies that an exception is thrown when a customer name is not specified
     */
    @Test
    public void sadPathCreateCustomerNoName() {
        CreateCustomerRequestDto requestDto = new CreateCustomerRequestDto();
        requestDto.setEmail("test123@test.com");
        assertThrows(Exception.class, () -> {
            customerService.createCustomer(requestDto);
        });
    }

    /**
     * Sad path for customer creation
     * Verifies that an exception is thrown when a customer email is not specified.
     */
    @Test
    public void sadPathCreateCustomerNoEmail() {
        CreateCustomerRequestDto requestDto = new CreateCustomerRequestDto();
        requestDto.setName("TestName");

        assertThrows(Exception.class, () -> {
            customerService.createCustomer(requestDto);
        });
    }

    /**
     * Sad path for customer creation
     * Verifies that an exception is thrown when no information is provided.
     */
    @Test
    public void sadPathCreateCustomerNoInformation() {
        CreateCustomerRequestDto requestDto = new CreateCustomerRequestDto();
        assertThrows(Exception.class, () -> customerService.createCustomer(requestDto));
    }

    /**
     * Happy path test for retrieving customer orders
     * Should return an empty list since the customer should not have any orders
     */
    @Test
    public void happyPathGetCustomerOrdersNoOrders() {
        Integer customerId = 1;
        try {
            List<OrderEntity> orders = customerService.getCustomerOrders(customerId);
            assertEquals(0, orders.size());
        } catch (Exception e) {
            throw new AssertionError("Exception thrown: " + e.getMessage());
        }
    }

    /**
     * Happy path test for retrieving customer orders
     * Should return a list of size 1 since the mocked order repository is configured to return 1 order.
     */
    @Test
    public void happyPathGetCustomerOrdersWithOneOrder() {
        //Init
        List<OrderEntity> orders = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerId(1);
        orderEntity.setId(2);
        orderEntity.setSku("123456");
        orderEntity.setCreatedAt(LocalDateTime.now());
        orderEntity.setTotal(BigDecimal.valueOf(300.00));
        orders.add(orderEntity);
        Integer customerId = 1;
        //When
        Mockito.when(mockOrderRepository.findByCustomerId(Mockito.anyInt())).thenReturn(orders);
        //Test
        try {
            List<OrderEntity> orderEntities = customerService.getCustomerOrders(customerId);
            assertEquals(1, orderEntities.size());
            assertEquals("123456", orderEntities.get(0).getSku());
            assertEquals(BigDecimal.valueOf(300.00), orderEntities.get(0).getTotal());
        } catch (Exception e) {
            throw new AssertionError("Exception thrown: " + e.getMessage());
        }
    }

    /**
     * Happy path test for retrieving customer orders
     * Should return a list of size 2 since the mocked order repository is configured to return 2 orders.
     */
    @Test
    public void happyPathGetCustomerOrdersWithManyOrders() {
        //Init
        List<OrderEntity> orders = new ArrayList<>();
        OrderEntity orderEntityOne = new OrderEntity();
        orderEntityOne.setCustomerId(1);
        orderEntityOne.setId(2);
        orderEntityOne.setSku("123456");
        orderEntityOne.setCreatedAt(LocalDateTime.now());
        orderEntityOne.setTotal(BigDecimal.valueOf(300.00));
        orders.add(orderEntityOne);

        OrderEntity orderEntityTwo = new OrderEntity();
        orderEntityTwo.setCustomerId(1);
        orderEntityTwo.setId(3);
        orderEntityTwo.setSku("78910");
        orderEntityTwo.setCreatedAt(LocalDateTime.now());
        orderEntityTwo.setTotal(BigDecimal.valueOf(499.99));
        orders.add(orderEntityTwo);

        Integer customerId = 1;

        //When
        Mockito.when(mockOrderRepository.findByCustomerId(Mockito.anyInt())).thenReturn(orders);

        //Test
        try {
            List<OrderEntity> orderEntities = customerService.getCustomerOrders(customerId);
            assertEquals(2, orderEntities.size());
            assertEquals("123456", orderEntities.get(0).getSku());
            assertEquals("78910", orderEntities.get(1).getSku());
        } catch (Exception e) {
            throw new AssertionError("Exception thrown: " + e.getMessage());
        }
    }

    /**
     * Sad path test for retrieving customer orders
     * Verifies that an exception is thrown when 0 is passed in for a customer id
     */
    @Test
    public void sadPathGetCustomerOrdersWithZeroId() {
        Integer customerId = 0;
        assertThrows(Exception.class, () -> customerService.getCustomerOrders(customerId));
    }

    /**
     * Sad path test for retrieving customer orders
     * Verifies that an exception is thrown when a negative number is passed in for a customer id
     */
    @Test
    public void sadPathGetCustomerOrdersWithNegativeId() {
        Integer customerId = -1;
        assertThrows(Exception.class, () -> customerService.getCustomerOrders(customerId));
    }

    @Test
    public void happyPathCreateOrderForCustomer() {
        //Init
        Integer customerId = 1;
        CreateOrderRequestDto skuRequestDto = new CreateOrderRequestDto();
        skuRequestDto.setSku("123456");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setName("Nintendo Switch");
        productEntity.setSku("123456");
        productEntity.setPrice(BigDecimal.valueOf(300.00));
        productEntity.setDescription("It's a Nintendo Switch!");
        //When
        Mockito.when(mockProductRepository.findBySku(Mockito.any(String.class))).thenReturn(productEntity);

        //Test
        try {
            OrderEntity testResult = customerService.createOrderForCustomer(customerId, skuRequestDto);
            assertNotNull(testResult);
            assertEquals(skuRequestDto.getSku(), testResult.getSku());
            assertEquals(customerId, testResult.getCustomerId());
        } catch (Exception e) {
            throw new AssertionError("Exception caught: " + e.getMessage());
        }
    }

    @Test
    public void sadPathCreateOrderForCustomerInvalidSku() {
        //Init
        Integer customerId = 1;
        CreateOrderRequestDto dto = new CreateOrderRequestDto();
        dto.setSku("invalid_sku");
        //Mock
        Mockito.when(mockProductRepository.findBySku(Mockito.any(String.class))).thenReturn(null);
        //Test
        assertThrows(Exception.class, () -> customerService.createOrderForCustomer(customerId, dto));
    }

    @Test
    public void sadPathCreateOrderForCustomerInvalidCustomerId() {
        //Init
        Integer customerId = 0;
        CreateOrderRequestDto dto = new CreateOrderRequestDto();
        dto.setSku("123456");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setName("Nintendo Switch");
        productEntity.setSku("123456");
        productEntity.setPrice(BigDecimal.valueOf(300.00));
        productEntity.setDescription("It's a Nintendo Switch!");
        //Mock
        Mockito.when(mockProductRepository.findBySku(Mockito.any(String.class))).thenReturn(productEntity);

        //Test
        assertThrows(Exception.class, () -> customerService.createOrderForCustomer(customerId, dto));
    }

    @Test
    public void sadPathCreateOrderForCustomerNegativeCustomerId() {
        //Init
        Integer customerId = -1;
        CreateOrderRequestDto dto = new CreateOrderRequestDto();
        dto.setSku("123456");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setName("Nintendo Switch");
        productEntity.setSku("123456");
        productEntity.setPrice(BigDecimal.valueOf(300.00));
        productEntity.setDescription("It's a Nintendo Switch!");
        //Mock
        Mockito.when(mockProductRepository.findBySku(Mockito.any(String.class))).thenReturn(productEntity);

        //Test
        assertThrows(Exception.class, () -> customerService.createOrderForCustomer(customerId, dto));
    }
}
