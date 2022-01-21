package com.creativelabs.shoppingapi.services;

import com.creativelabs.shoppingapi.entities.Product;
import com.creativelabs.shoppingapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    List<Product> products = new ArrayList<>();

    {
        for (int i = 1; i <= 10; i++) {
            int[] array = {1, 2, 3};
            int rnd = new Random().nextInt(array.length);
            products.add(new Product(i, "XXX-" + i, "Product" + i, "",
                    Math.random() * 1000, "", array[rnd]));
        }
    }


    @Override
    public List<Product> getAllProducts(int categoryId) {

        if (categoryId > 0) {
            return getAllProductsByCategory(categoryId);
        }
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getOneProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
//        Product product = products.get(id);
        return product;
    }

    @Override
    public List<Product> getAllProductsByCategory(int categoryId) {
//        return products.stream().filter(product -> product.getCategoryId() == categoryId)
//                .collect(Collectors.toList());

        return productRepository.findAllByCategoryId(categoryId);
    }
}
