package edu.fscj.cen3024c.m7pizzarestaurant.controller;

import edu.fscj.cen3024c.m7pizzarestaurant.dto.CustomerDTO;
import edu.fscj.cen3024c.m7pizzarestaurant.exceptions.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.ResponseEntity;

import edu.fscj.cen3024c.m7pizzarestaurant.service.CustomerService;

// SLF4J Logger and LoggerFactory
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// SLF4J Profiler
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    // Declare a Logger instance
    private static final Logger logger =
            LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Other methods remain unchanged

    @PostMapping("/create")
    public ResponseEntity<CustomerDTO> createCustomer(
            @RequestBody CustomerCreationRequest request) {

        Profiler profiler = new Profiler("createCustomer");
        profiler.start("Create Customer");

       // profiler.stop().start("Create Customer");
        try {
            CustomerDTO customerDTO = new CustomerDTO();

            customerDTO.setLastName(request.getLastName());
            customerDTO.setFirstName(request.getFirstName());
            customerDTO.setAddress(request.getAddress());
            customerDTO.setCity(request.getCity());
            customerDTO.setState(request.getState());
            customerDTO.setPostalCode(request.getPostalCode());
            customerDTO.setEMail(request.getEMail());
            customerDTO.setPhoneNumber(request.getPhoneNumber());
            customerDTO.setUserId(request.getUserId());
            customerDTO.setPassword(request.getPassword());

            if (customerDTO.getUserId().equals("testinvaliddata"))
                    throw new InvalidDataException("invalid data!");
            else if (customerDTO.getUserId().equals("testnosuchalgorithm"))
                throw new InvalidDataException("no such algorithm!");

            CustomerDTO createdCustomer =
                    customerService.createCustomer(customerDTO);

            // Log the creation
            logger.info("A new customer has been added: {}", createdCustomer.getUserId());
            return ResponseEntity.ok(createdCustomer);
        } catch (InvalidDataException e) {
            // Log the error
            logger.error("Error creating customer", e);
            return ResponseEntity.internalServerError().body(null);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error creating customer", e);
            throw new RuntimeException(e);
        }
        finally {
            TimeInstrument ti = profiler.stop();
            ti.print();
        }
    }

    // Inner class to encapsulate customer creation request
    static class CustomerCreationRequest {
        private String lastName;
        private String firstName;
        private String address;
        private String city;
        private String state;
        private String postalCode;
        private String eMail;
        private String phoneNumber;
        private String userId;
        private String password;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getEMail() {
            return eMail;
        }

        public void setEMail(String eMail) {
            this.eMail = eMail;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
