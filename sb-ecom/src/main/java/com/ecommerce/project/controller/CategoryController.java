package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try {
            String message = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId){
        try{
            String message = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>(message, HttpStatus.OK );
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}

