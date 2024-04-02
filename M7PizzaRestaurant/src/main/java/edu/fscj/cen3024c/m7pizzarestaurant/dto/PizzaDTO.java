package edu.fscj.cen3024c.m7pizzarestaurant.dto;

import jakarta.persistence.Column;

public class PizzaDTO {
    private String pizzaType;
    private int pizzaSize;
    private String crustType;

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public int getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(int pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public String getCrustType() {
        return crustType;
    }

    public void setCrustType(String crustType) {
        this.crustType = crustType;
    }
}
