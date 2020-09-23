package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.models.Category;
import com.lambdaschool.secretfamilyrecipe.models.Ingredient;


public interface CategoryService {
    Category findCategoryById(long id);

    Category save(Category category);

    Category update(Category category, long id);
}
