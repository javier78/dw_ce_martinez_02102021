package com.javi.ShopService.controller;

import com.javi.ShopService.service.CustomerService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Mock
    private CustomerService mockCustomerService;
    @Mock
    private ModelMapper mockModelMapper;

    
}
