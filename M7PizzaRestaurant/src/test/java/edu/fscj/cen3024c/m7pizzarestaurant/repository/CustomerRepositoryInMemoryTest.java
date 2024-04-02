package edu.fscj.cen3024c.m7pizzarestaurant.repository;

import edu.fscj.cen3024c.m7pizzarestaurant.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryInMemoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void createCustomer_ShouldReturnNewCustomer() {
        // Given
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("John");
        newCustomer.setLastName("Doe");
        newCustomer.setAddress("123 Main St");
        newCustomer.setCity("Springfield");
        newCustomer.setState("FL");
        newCustomer.setPostalCode("12345");
        newCustomer.setPhoneNumber("123-456-7890");
        newCustomer.setEMail("john.doe@example.com");
        newCustomer.setUserId("johndoe");
        // ignore storedHash and storedSalt

        // When
        Customer savedCustomer = customerRepository.save(newCustomer);

        // Then
        Customer foundCustomer = entityManager.find(Customer.class, savedCustomer.getCustomerid());

        assertThat(foundCustomer).isEqualTo(savedCustomer);
    }
}