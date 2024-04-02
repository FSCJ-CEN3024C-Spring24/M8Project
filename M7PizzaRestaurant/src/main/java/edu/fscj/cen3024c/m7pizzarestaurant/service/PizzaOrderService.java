package edu.fscj.cen3024c.m7pizzarestaurant.service;

import edu.fscj.cen3024c.m7pizzarestaurant.entity.PizzaOrder;
import edu.fscj.cen3024c.m7pizzarestaurant.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaOrderService {
    private final PizzaOrderRepository pizzaOrderRepository;

    @Autowired
    public PizzaOrderService(PizzaOrderRepository pizzaOrderRepository) {
        this.pizzaOrderRepository = pizzaOrderRepository;
    }

    public List<PizzaOrder> getAllPizzaOrders() {
        return pizzaOrderRepository.findAll();
    }
}