package edu.fscj.cen3024c.m7pizzarestaurant.converter;

import edu.fscj.cen3024c.m7pizzarestaurant.dto.CustomerDTO;
import edu.fscj.cen3024c.m7pizzarestaurant.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    // Method to convert from Entity to DTO
    public CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setLastName(customer.getLastName());
        dto.setFirstName(customer.getFirstName());
        dto.setAddress(customer.getAddress());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setEMail(customer.getEMail());
        dto.setUserId(customer.getUserId());
        return dto;
    }

    // Method to convert from DTO to Entity
    public Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setLastName(dto.getLastName());
        customer.setFirstName(dto.getFirstName());
        customer.setAddress(dto.getAddress());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setEMail(dto.getEMail());
        customer.setUserId(dto.getUserId());
        return customer;
    }
}
