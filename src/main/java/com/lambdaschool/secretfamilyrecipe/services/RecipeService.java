package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.models.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAll();

    Recipe findRecipeById(long recipeid);

    void delete(long id);

    Recipe update(Recipe recipe, long recipeid);

    Recipe save(Recipe recipe);

}
