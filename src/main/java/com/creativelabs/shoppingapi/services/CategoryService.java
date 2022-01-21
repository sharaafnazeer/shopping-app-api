package com.creativelabs.shoppingapi.services;

import com.creativelabs.shoppingapi.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public List<Category> getAlCategories();

    public Optional<Category> getOneCategory(int id);
}
