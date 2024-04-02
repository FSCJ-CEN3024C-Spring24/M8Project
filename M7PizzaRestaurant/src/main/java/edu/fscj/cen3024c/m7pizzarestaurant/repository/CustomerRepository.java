package edu.fscj.cen3024c.m7pizzarestaurant.repository;

import edu.fscj.cen3024c.m7pizzarestaurant.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // find by last name
    List<Customer> findByLastName(String lastName);
    // find by user id
    @Query(
            "" +
                    "SELECT CASE WHEN COUNT(c) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM Customer c " +
                    "WHERE c.userId = ?1"
    )
    Boolean selectExistsUserId(String userId);
    Optional<Customer> findByUserId(String userId);
}