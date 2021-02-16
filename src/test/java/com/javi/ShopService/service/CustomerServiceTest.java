package com.javi.ShopService.service;

import com.javi.ShopService.dto.CreateCustomerRequestDto;
import com.javi.ShopService.entity.CustomerEntity;
import com.javi.ShopService.repository.CustomerRepository;
import com.javi.ShopService.repository.OrderRepository;
import com.javi.ShopService.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    private static final String MOCK_OUTPUT = "Mocked show label";

    @Spy
    private CustomerRepository mockCustomerRepository;

    @Mock
    private OrderRepository mockOrderRepository;

    @Mock
    private ProductRepository mockProductRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    private void before() {

    }

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

    @Test
    public void sadPathCreateCustomerNoName() {
        CreateCustomerRequestDto requestDto = new CreateCustomerRequestDto();
        requestDto.setEmail("test123@test.com");
        assertThrows(Exception.class, () -> {
            customerService.createCustomer(requestDto);
        });
    }

    @Test
    public void sadPathCreateCustomerNoEmail() {
        CreateCustomerRequestDto requestDto = new CreateCustomerRequestDto();
        requestDto.setName("TestName");

        assertThrows(Exception.class, () -> {
            customerService.createCustomer(requestDto);
        });
    }

    @Test
    public void

}