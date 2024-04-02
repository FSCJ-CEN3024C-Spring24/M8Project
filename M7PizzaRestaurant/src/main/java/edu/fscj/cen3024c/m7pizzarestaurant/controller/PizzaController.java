package edu.fscj.cen3024c.m7pizzarestaurant.controller;

import edu.fscj.cen3024c.m7pizzarestaurant.dto.PizzaDTO;
import edu.fscj.cen3024c.m7pizzarestaurant.entity.Pizza;
import edu.fscj.cen3024c.m7pizzarestaurant.service.PizzaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        return ResponseEntity.ok(pizzas);
    }

    @CrossOrigin(origins = "http://example2.com",
            methods = {RequestMethod.GET, RequestMethod.POST},
            allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/{pizzatype}")
    public ResponseEntity<List<PizzaDTO>>
            getPizzaByPizzaType(@PathVariable(value = "pizzatype") String type) {

        List<PizzaDTO> pizzaList = pizzaService.getPizzaDTOByPizzaType(type);
        return ResponseEntity.ok().body(pizzaList);
    }
}
