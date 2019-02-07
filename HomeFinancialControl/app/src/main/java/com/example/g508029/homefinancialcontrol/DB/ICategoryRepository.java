package com.example.g508029.homefinancialcontrol.DB;

import com.example.g508029.homefinancialcontrol.model.Category;

import java.util.List;

public interface ICategoryRepository {
    void addCategory(Category category);
    List<Category> getAll();
    void deleteCategoryById(String categoryId);
}
