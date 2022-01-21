package com.creativelabs.shoppingapi.repositories;

import com.creativelabs.shoppingapi.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> getAllByUserId(int userId);
}
