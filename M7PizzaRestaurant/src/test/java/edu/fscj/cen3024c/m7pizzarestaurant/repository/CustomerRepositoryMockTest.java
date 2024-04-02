package edu.fscj.cen3024c.m7pizzarestaurant.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.fscj.cen3024c.m7pizzarestaurant.entity.Customer;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryMockTest {

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testFindByUserId() {
        // Given
        String userId = "user123";
        Customer expectedCustomer = new Customer();
        expectedCustomer.setUserId(userId);
        expectedCustomer.setFirstName("John");
        expectedCustomer.setLastName("Doe");
        when(customerRepository.findByUserId(userId))
                .thenReturn(Optional.of(expectedCustomer));

        // When
        // Use the mocked repository object
        Optional<Customer> actualCustomer = customerRepository.
                findByUserId(userId);

        // Then
        assertTrue(actualCustomer.isPresent(), "The customer should be found");
        assertEquals(expectedCustomer.getUserId(),
                actualCustomer.get().getUserId(), "The user ID should match");
        assertEquals(expectedCustomer.getFirstName(),
                actualCustomer.get().getFirstName(), "The first name should match");
        assertEquals(expectedCustomer.getLastName(),
                actualCustomer.get().getLastName(), "The last name should match");

        // Verify repository method was called
        verify(customerRepository).findByUserId(userId);
    }
}
