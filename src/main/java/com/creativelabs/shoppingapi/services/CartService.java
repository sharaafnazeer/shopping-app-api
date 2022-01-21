package com.creativelabs.shoppingapi.services;

import com.creativelabs.shoppingapi.entities.Cart;

import java.util.List;

public interface CartService {

    public List<Cart> getAllCartItems();
    public List<Cart> getCartItemsByUser(int userId);

    public Cart updateCart(Cart cart, int cartId);
    public Cart addCart(Cart cart);
    public boolean deleteCart(int cartId);
    public Cart getCart(int cartId);
}
