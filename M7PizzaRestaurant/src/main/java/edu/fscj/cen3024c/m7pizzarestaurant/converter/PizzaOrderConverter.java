package edu.fscj.cen3024c.m7pizzarestaurant.converter;

import edu.fscj.cen3024c.m7pizzarestaurant.dto.PizzaOrderDTO;
import edu.fscj.cen3024c.m7pizzarestaurant.entity.Customer;
import edu.fscj.cen3024c.m7pizzarestaurant.entity.Pizza;
import edu.fscj.cen3024c.m7pizzarestaurant.entity.PizzaOrder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PizzaOrderConverter {
    public PizzaOrderDTO convertToDTO(PizzaOrder PizzaOrder) {
        PizzaOrderDTO dto = new PizzaOrderDTO();

        dto.setCustomer(PizzaOrder.getCustomer());
        dto.setPizza(PizzaOrder.getPizza());
        dto.setOrderDate(PizzaOrder.getOrderDate());
        return dto;
    }

    public PizzaOrder converttoPizzaOrder(PizzaOrderDTO pizzaOrderDTO) {
        PizzaOrder pizzaOrder = new PizzaOrder();

        pizzaOrder.setCustomer(pizzaOrderDTO.getCustomer());
        pizzaOrder.setPizza(pizzaOrderDTO.getPizza());
        pizzaOrder.setOrderDate(pizzaOrderDTO.getOrderDate());
        return pizzaOrder;
    }
}
