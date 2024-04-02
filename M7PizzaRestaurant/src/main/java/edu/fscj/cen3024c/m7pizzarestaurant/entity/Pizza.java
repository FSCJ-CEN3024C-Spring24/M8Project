package edu.fscj.cen3024c.m7pizzarestaurant.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Pizza {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "pizzaid", nullable = false)
    private int pizzaid;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pizza")
    private Set<PizzaOrder> pizzaOrder;

    @Column(name = "pizzatype", length = 255)
    private String pizzaType;

    @Column(name = "pizzasize", length = 10)
    private int pizzaSize;

    @Column(name = "crusttype", length = 20)
    private String crustType;

    public int getPizzaid() {
        return pizzaid;
    }

    public void setPizzaid(int pizzaid) {
        this.pizzaid = pizzaid;
    }

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

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o)
            result = true;
        else if (o != null && getClass() != o.getClass()) {
            Pizza pizza = (Pizza) o;
            result = pizzaid == pizza.pizzaid && pizzaSize == pizza.pizzaSize &&
                Objects.equals(pizzaType, pizza.pizzaType);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaid, pizzaType, pizzaSize);
    }

    @Override
    public String toString() {
        return "Pizza {" +
                "pizzaid=" + pizzaid +
                ", pizzaOrder=" + pizzaOrder +
                ", pizzaType='" + pizzaType + '\'' +
                ", pizzaSize=" + pizzaSize +
                ", crustType='" + crustType + '\'' +
                " }";
    }
}
