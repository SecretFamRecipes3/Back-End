package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.models.Category;

import java.util.List;


public interface CategoryService {
    List<Category> findAll();

    Category findCategoryById(long id);

    void delete(long id);

    Category save(Category category);

    Category update(Category category, long id);
}
