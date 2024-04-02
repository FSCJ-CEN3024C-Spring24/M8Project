package edu.fscj.cen3024c.m7pizzarestaurant.service;

import edu.fscj.cen3024c.m7pizzarestaurant.dto.CustomerDTO;
import edu.fscj.cen3024c.m7pizzarestaurant.dto.PizzaDTO;
import edu.fscj.cen3024c.m7pizzarestaurant.entity.Customer;
import edu.fscj.cen3024c.m7pizzarestaurant.entity.Pizza;
import edu.fscj.cen3024c.m7pizzarestaurant.converter.PizzaConverter;
import edu.fscj.cen3024c.m7pizzarestaurant.exceptions.ResourceNotFoundException;
import edu.fscj.cen3024c.m7pizzarestaurant.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaConverter pizzaConverter;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository,
                        PizzaConverter pizzaConverter) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaConverter = pizzaConverter;
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public List<PizzaDTO> getAllPizzaDTOs() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream()
                .map(pizzaConverter::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<Pizza> getPizzaByPizzaType(String type) {
        return pizzaRepository.findByPizzaType(type);
    }

    public List<PizzaDTO> getPizzaDTOByPizzaType(String type) {
        List<Pizza> pizzas = pizzaRepository.findByPizzaType(type);
        if (pizzas.isEmpty()) {
            throw new ResourceNotFoundException("Pizza not found with type: " + type);
        }
        return pizzas.stream()
                .map(pizzaConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
