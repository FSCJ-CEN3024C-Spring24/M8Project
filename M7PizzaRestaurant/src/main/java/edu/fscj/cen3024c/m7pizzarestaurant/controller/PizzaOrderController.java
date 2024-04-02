package edu.fscj.cen3024c.m7pizzarestaurant.controller;

import edu.fscj.cen3024c.m7pizzarestaurant.entity.PizzaOrder;
import edu.fscj.cen3024c.m7pizzarestaurant.service.PizzaOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizzaorders")
public class PizzaOrderController {
    private final PizzaOrderService pizzaOrderService;

    @Autowired
    public  PizzaOrderController(PizzaOrderService pizzaOrderService) {
        this.pizzaOrderService = pizzaOrderService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaOrder>> getAllPizzaOrders() {
        List<PizzaOrder> pizzaOrders = pizzaOrderService.getAllPizzaOrders();
        return ResponseEntity.ok(pizzaOrders);
    }
}
