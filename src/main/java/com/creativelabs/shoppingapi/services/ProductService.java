package com.creativelabs.shoppingapi.services;

import com.creativelabs.shoppingapi.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> getAllProducts(int categoryId);

    public Optional<Product> getOneProduct(int id);

    public List<Product> getAllProductsByCategory(int categoryId);
}
