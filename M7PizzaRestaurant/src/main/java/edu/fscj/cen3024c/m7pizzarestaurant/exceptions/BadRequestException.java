package edu.fscj.cen3024c.m7pizzarestaurant.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
