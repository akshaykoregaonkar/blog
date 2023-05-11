package com.akshay.blog.controller;

import com.akshay.blog.model.Category;
import com.akshay.blog.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  @Autowired private CategoryRepository categoryRepository;

  @GetMapping
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    Optional<Category> category = categoryRepository.findById(id);
    return category.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
