package com.creativelabs.shoppingapi.services;

import com.creativelabs.shoppingapi.entities.Cart;
import com.creativelabs.shoppingapi.entities.Order;
import com.creativelabs.shoppingapi.entities.OrderItem;

import java.util.List;

public interface OrderService {

    List<Order> getOrders(int status);
    List<Order> getOrdersByUser(int status, int userId);
    Order saveOrder(int userId);
}
