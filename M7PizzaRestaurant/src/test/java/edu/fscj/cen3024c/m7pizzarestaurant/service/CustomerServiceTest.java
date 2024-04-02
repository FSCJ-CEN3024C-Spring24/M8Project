package edu.fscj.cen3024c.m7pizzarestaurant.service;
import edu.fscj.cen3024c.m7pizzarestaurant.converter.CustomerConverter;
import edu.fscj.cen3024c.m7pizzarestaurant.dto.CustomerDTO;
import edu.fscj.cen3024c.m7pizzarestaurant.entity.Customer;
import edu.fscj.cen3024c.m7pizzarestaurant.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerConverter customerConverter;
    @InjectMocks
    private CustomerService customerService;
    @Test
    void getAllCustomerDTOs_ShouldReturnCustomerDTOList() {
        // Given
        String userId = "user123";
        Customer expectedCustomer = new Customer();
        expectedCustomer.setUserId(userId);
        expectedCustomer.setFirstName("John");
        expectedCustomer.setLastName("Doe");
        CustomerDTO expectedCustomerDTO = new CustomerDTO();
        expectedCustomerDTO.setUserId(userId);
        expectedCustomerDTO.setFirstName("John");
        expectedCustomerDTO.setLastName("Doe");

        when(customerRepository.findAll()).
                thenReturn(Arrays.asList(expectedCustomer));
        when(customerConverter.convertToDTO(any(Customer.class))).
                thenReturn(expectedCustomerDTO);

        // When
        List<CustomerDTO> result = customerService.getAllCustomerDTOs();

        // Then
        assertThat(result).isNotNull().isNotEmpty();
        verify(customerRepository).findAll();
        verify(customerConverter).convertToDTO(any(Customer.class));
    }
}