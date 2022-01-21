package com.creativelabs.shoppingapi.controllers;

import com.creativelabs.shoppingapi.entities.Cart;
import com.creativelabs.shoppingapi.entities.Product;
import com.creativelabs.shoppingapi.models.MessageResponse;
import com.creativelabs.shoppingapi.services.CartService;
import com.creativelabs.shoppingapi.services.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getAllCartItems(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return cartService.getCartItemsByUser(userDetails.getId().intValue());
    }

    @PostMapping
    public ResponseEntity<?> addCart(Authentication authentication, @RequestBody Cart cart) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        cart.setUserId(userDetails.getId().intValue());
        cartService.addCart(cart);
        return ResponseEntity
                .ok(new MessageResponse("Product added to cart successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(@PathVariable("id") int id, @RequestBody Cart cart) {
        Cart availableCart = cartService.getCart(id);
        if (availableCart != null) {
            availableCart.setQuantity(cart.getQuantity());
            cartService.updateCart(availableCart, id);

            return ResponseEntity
                    .ok(new MessageResponse("Product updated to cart successfully!"));
        }

        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Cart item not available"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") int id) {
        if (cartService.getCart(id) != null) {
            cartService.deleteCart(id);

            return ResponseEntity
                    .ok(new MessageResponse("Product deleted from cart successfully!"));
        }

        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Cart item not available"));

    }
}
