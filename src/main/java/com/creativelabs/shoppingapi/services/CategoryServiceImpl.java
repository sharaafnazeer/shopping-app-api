package com.creativelabs.shoppingapi.services;

import com.creativelabs.shoppingapi.entities.Category;
import com.creativelabs.shoppingapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAlCategories() {
        return this.categoryRepository.findAll();
//        return categories;
    }

    @Override
    public Optional<Category> getOneCategory(int id) {
        return categoryRepository.findById(id);
    }
}
