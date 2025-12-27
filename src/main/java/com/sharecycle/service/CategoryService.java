package com.sharecycle.service;

import com.sharecycle.entity.Category;
import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long id);   // ← ADD THIS

    List<Category> getAllCategories();

    Category createCategory(Category category);
}
