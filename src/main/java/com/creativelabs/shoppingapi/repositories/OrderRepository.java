package com.creativelabs.shoppingapi.repositories;

import com.creativelabs.shoppingapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByStatus(int status);
    List<Order> findAllByStatusAndUserId(int status, int userId);
    List<Order> findAllByUserId(int userId);

}
