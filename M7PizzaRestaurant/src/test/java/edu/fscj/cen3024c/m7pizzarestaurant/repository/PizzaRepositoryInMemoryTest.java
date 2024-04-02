package edu.fscj.cen3024c.m7pizzarestaurant.repository;

import edu.fscj.cen3024c.m7pizzarestaurant.entity.Pizza;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PizzaRepositoryInMemoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    public void createPizza_ShouldReturnNewPizza() {
        // Given
        Pizza newPizza = new Pizza();
        newPizza.setPizzaType("Pineapple");
        newPizza.setPizzaSize(14);
        newPizza.setCrustType("New York");

        // When
        Pizza savedPizza = pizzaRepository.save(newPizza);

        // Then
        Pizza foundPizza = entityManager.find(Pizza.class, savedPizza.getPizzaid());

        assertThat(foundPizza).isEqualTo(savedPizza);
    }
}