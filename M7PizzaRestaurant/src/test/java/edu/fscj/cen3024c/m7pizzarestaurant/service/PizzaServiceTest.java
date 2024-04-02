package edu.fscj.cen3024c.m7pizzarestaurant.service;

import edu.fscj.cen3024c.m7pizzarestaurant.converter.PizzaConverter;
import edu.fscj.cen3024c.m7pizzarestaurant.dto.PizzaDTO;
import edu.fscj.cen3024c.m7pizzarestaurant.entity.Pizza;
import edu.fscj.cen3024c.m7pizzarestaurant.repository.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceTest {
    @Mock
    private PizzaRepository pizzaRepository;
    @Mock
    private PizzaConverter pizzaConverter;
    @InjectMocks
    private PizzaService pizzaService;
    @Test
    void getAllPizzaDTOs_ShouldReturnPizzaDTOList() {
        // Given
        Pizza expectedPizza = new Pizza();
        expectedPizza.setPizzaType("Pineapple");
        expectedPizza.setPizzaSize(14);
        expectedPizza.setCrustType("New York");
        PizzaDTO expectedPizzaDTO = new PizzaDTO();
        expectedPizzaDTO.setPizzaType("Pineapple");
        expectedPizzaDTO.setPizzaSize(14);
        expectedPizzaDTO.setCrustType("New York");

        when(pizzaRepository.findAll()).
                thenReturn(Arrays.asList(expectedPizza));
        when(pizzaConverter.convertToDTO(any(Pizza.class))).
                thenReturn(expectedPizzaDTO);

        // When
        List<PizzaDTO> result = pizzaService.getAllPizzaDTOs();

        // Then
        assertThat(result).isNotNull().isNotEmpty();
        verify(pizzaRepository).findAll();
        verify(pizzaConverter).convertToDTO(any(Pizza.class));
    }
}