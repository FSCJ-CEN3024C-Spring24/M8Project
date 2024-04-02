package edu.fscj.cen3024c.m7pizzarestaurant.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import java.util.Objects;

@Entity
@Table(name = "pizzaorder")
public class PizzaOrder {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "orderid", nullable = false)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "customerid", referencedColumnName = "customerid")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "pizzaid", referencedColumnName = "pizzaid")
    private Pizza pizza;

    @JoinColumn(name = "orderdate")
    LocalDate orderDate;

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaOrder that = (PizzaOrder) o;
        return customer.equals(that.customer) &&
                pizza.equals(that.pizza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, pizza);
    }

    @Override
    public String toString() {
        return "PizzaOrder {" +
                "orderId=" + orderId +
                ", customer=" + customer.getLastName() +
                " }";
    }
}
