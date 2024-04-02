package edu.fscj.cen3024c.m7pizzarestaurant.service;

import edu.fscj.cen3024c.m7pizzarestaurant.converter.CustomerConverter;
import edu.fscj.cen3024c.m7pizzarestaurant.dto.CustomerDTO;
import edu.fscj.cen3024c.m7pizzarestaurant.entity.Customer;
import edu.fscj.cen3024c.m7pizzarestaurant.exceptions.BadRequestException;
import edu.fscj.cen3024c.m7pizzarestaurant.exceptions.ResourceNotFoundException;
import edu.fscj.cen3024c.m7pizzarestaurant.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    public List<CustomerDTO> getAllCustomerDTOs() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerDTOByUserId(String userId) {
        Customer customer = customerRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with userId: " + userId));
        return customerConverter.convertToDTO(customer);
    }

    private byte[] createSalt() {
        var random = new SecureRandom();
        var salt = new byte[128];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] createPasswordHash(String password, byte[] salt)
            throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        return md.digest(
                password.getBytes(StandardCharsets.UTF_8));
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO)
            throws NoSuchAlgorithmException {
        var customer = customerConverter.convertToEntity(customerDTO);
        if (customerDTO.getPassword().isBlank()) throw new
                IllegalArgumentException(
                "Password is required."
        );
        var existsUserId =
                customerRepository.selectExistsUserId(customer.getUserId());
        if (existsUserId) throw new BadRequestException(
                "Email " + customer.getUserId() + " taken"
        );
        byte[] salt = createSalt();
        byte[] hashedPassword =
                createPasswordHash(customerDTO.getPassword(), salt);
        customer.setStoredSalt(salt);
        customer.setStoredHash(hashedPassword);
        customerRepository.save(customer);
        //return customerConverter.convertToDTO(customer);
        return customerDTO;
    }
}