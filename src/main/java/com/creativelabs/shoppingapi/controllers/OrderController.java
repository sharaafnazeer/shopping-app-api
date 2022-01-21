package com.creativelabs.shoppingapi.controllers;

import com.creativelabs.shoppingapi.entities.Cart;
import com.creativelabs.shoppingapi.entities.Order;
import com.creativelabs.shoppingapi.entities.Product;
import com.creativelabs.shoppingapi.models.MessageResponse;
import com.creativelabs.shoppingapi.services.OrderService;
import com.creativelabs.shoppingapi.services.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders(Authentication authentication, @RequestParam(name = "status",
                                 defaultValue = "-1") int status) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return orderService.getOrdersByUser(status, userDetails.getId().intValue());
    }

    @PostMapping
    public ResponseEntity<?> saveOrder(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        orderService.saveOrder(userDetails.getId().intValue());
        return ResponseEntity
                .ok(new MessageResponse("Order created successfully!"));
    }

}
