package com.creativelabs.shoppingapi.services;

import com.creativelabs.shoppingapi.entities.Cart;
import com.creativelabs.shoppingapi.entities.Product;
import com.creativelabs.shoppingapi.repositories.CartRepository;
import com.creativelabs.shoppingapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    List<Cart> carts = new ArrayList<>();

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }
//
//    {
//        for (int i = 1; i <= 10; i++) {
//            int[] array = {1, 2, 3, 4, 5};
//            int rnd = new Random().nextInt(array.length);
//            this.carts.add(new Cart(i, array[rnd], "Product"+array[rnd], array[rnd],
//                    Math.random() * 1000, Math.random() * 10000, ""));
//        }
//    }


    @Override
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> getCartItemsByUser(int userId) {
        return cartRepository.getAllByUserId(userId);
    }

    @Override
    public Cart updateCart(Cart cart, int cartId) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart addCart(Cart cart) {

        Optional<Product> productOptional = productRepository.findById(cart.getProductId());
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            cart.setProductName(product.getName());
            cart.setProductPrice(product.getPrice());
            cart.setTotalPrice(product.getPrice() * cart.getQuantity());
            cart.setImage(product.getImage());

            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public boolean deleteCart(int cartId) {
        cartRepository.deleteById(cartId);
        return true;
    }

    @Override
    public Cart getCart(int cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cart.orElse(null);
    }
}
