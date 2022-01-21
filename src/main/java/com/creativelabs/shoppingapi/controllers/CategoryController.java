package com.creativelabs.shoppingapi.controllers;

import com.creativelabs.shoppingapi.entities.Category;
import com.creativelabs.shoppingapi.services.CategoryService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAlCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getOneCategory(@PathVariable("id") int id) {
        Optional<Category> categoryOptional = categoryService.getOneCategory(id);
        if (categoryOptional.isPresent()) {
            return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
