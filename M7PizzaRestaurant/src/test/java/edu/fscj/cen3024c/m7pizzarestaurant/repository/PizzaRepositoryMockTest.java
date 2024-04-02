package edu.fscj.cen3024c.m7pizzarestaurant.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.fscj.cen3024c.m7pizzarestaurant.entity.Pizza;

import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class PizzaRepositoryMockTest {

    @Mock
    private PizzaRepository pizzaRepository;

    @Test
    public void testFindByUserId() {
        // Given
        String pizzaType = "Pineapple";
        Pizza expectedPizza = new Pizza();
        expectedPizza.setPizzaType(pizzaType);
        expectedPizza.setPizzaSize(14);
        expectedPizza.setCrustType("New York");

        List<Pizza> mockPizzaList = new ArrayList<>();
        mockPizzaList.add(expectedPizza);
        when(pizzaRepository.findByPizzaType(pizzaType)).thenReturn(mockPizzaList);

        // When
        // Use the mocked repository object
        List<Pizza> actualPizzas = pizzaRepository.
                findByPizzaType(pizzaType);

        // Then
        assertFalse(actualPizzas.isEmpty(), "The pizza should be found");

        Pizza actualPizza = actualPizzas.get(0);
        assertEquals(expectedPizza.getPizzaType(),
                actualPizza.getPizzaType(), "The pizza type should match");
        assertEquals(expectedPizza.getPizzaSize(),
                actualPizza.getPizzaSize(), "The pizza size should match");
        assertEquals(expectedPizza.getCrustType(),
                actualPizza.getCrustType(), "The crust type should match");

        // Verify repository method was called
        verify(pizzaRepository).findByPizzaType(pizzaType);
    }
}
