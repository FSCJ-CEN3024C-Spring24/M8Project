package edu.fscj.cen3024c.m7pizzarestaurant.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
